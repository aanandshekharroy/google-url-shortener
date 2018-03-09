package com.example.theseus.urlshortener.ui.home

import com.example.theseus.urlshortener.R
import com.example.theseus.urlshortener.capture
import com.example.theseus.urlshortener.data.IDataManager
import com.example.theseus.urlshortener.data.api.model.response.UrlShortenResponse
import com.example.theseus.urlshortener.data.db.ShortUrl
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import junitparams.JUnitParamsRunner
import junitparams.Parameters
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers
import org.mockito.Captor
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.atLeast
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.io.IOException
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit
import org.mockito.Mockito.`when` as _when

@RunWith(JUnitParamsRunner::class)
class HomePresenterTest {
    @Mock
    lateinit var mDataManager: IDataManager
    @Mock
    lateinit var homeActivity: IHomeView
    @Mock
    lateinit var mCompositeDisposable: CompositeDisposable
    @InjectMocks
    lateinit var mPresenter: HomePresenter<IHomeView>
    @Captor
    lateinit var captor: ArgumentCaptor<ShortUrl>
    val urlShortenResponse = UrlShortenResponse("", "", "")
    private val immediate = object : Scheduler() {
        override fun scheduleDirect(run: Runnable,
                                    delay: Long, unit: TimeUnit): Disposable {
            return super.scheduleDirect(run, 0, unit)
        }

        override fun createWorker(): Scheduler.Worker {
            return ExecutorScheduler.ExecutorWorker(
                    Executor { it.run() })
        }
    }

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        val shortUrlList = mock(List::class.java) as List<ShortUrl>
        _when(mDataManager.fetchShortUrlsFromDatabase())
                .thenReturn(Flowable.just<List<ShortUrl>>(shortUrlList))
        RxJavaPlugins.setInitIoSchedulerHandler { immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }
    }

    @Test
    fun shouldOpenIntroSliderIfIntroNeverShown() {
        //given
        _when(mDataManager.isIntroSliderShown()).thenReturn(false)
        //when
        mPresenter.onAttach(homeActivity)
        //then
        verify(homeActivity).openIntroSlider()
    }

    @Test
    fun shouldNotOpenIntroWhenIntroShown() {
        //given
        val shortUrlList = mock(List::class.java) as List<ShortUrl>
        _when(mDataManager.isIntroSliderShown()).thenReturn(true)

        //when
        mPresenter.onAttach(homeActivity)
        //then
        verify(homeActivity, Mockito.never()).openIntroSlider()
    }

    @Test
    @Parameters(method = "getValidUrls")
    @Throws(Exception::class)
    fun testValidSiteAddressUsingRegex(url: String) {
        assertTrue("$url is not a valid url ", mPresenter.isValidAddress(url))
    }

    @Test
    @Parameters(method = "getInvalidUrls")
    @Throws(Exception::class)
    fun testInvalidSiteAddressUsingRegex(url: String) {
        assertFalse("$url is not invalid", mPresenter.isValidAddress(url))
    }
    fun getInvalidUrls() = arrayOf(arrayOf("//stackoverflow.com"), arrayOf("ww..google.com"))
    fun getValidUrls() = arrayOf(arrayOf("www.google.com"),
            arrayOf("blog.bufferapp.com/"),
            arrayOf("https://www.amazon.com/Kotlin-Programming-Cookbook-Explore-applications/dp/1788472144/ref=sr_1_1?ie=UTF8&qid=1517984118&sr=8-1&keywords=kotlin+programming+cookbook"),
            arrayOf("invalid_url"))

    @Test
    @Parameters(method = "getInvalidUrls")
    @Throws(Exception::class)
    fun shouldShowSnackbarIfAddressInvalid(url: String) {
        //given
        mPresenter.onAttach(homeActivity)
        //when
        mPresenter.shortenUrlClicked(url)
        //then
        verify(homeActivity).showSnackbar(R.string.invalid_url)
    }

    @Test
    @Throws(Exception::class)
    fun shouldShowProgressDialogWhileFetchingShortUrl() {
        //given
        val url = "www.google.com"
        _when(mDataManager.isIntroSliderShown()).thenReturn(true)
        _when(mDataManager.fetchShortUrl(ArgumentMatchers.anyString()))
                .thenReturn(Single.just(urlShortenResponse))
        mPresenter.onAttach(homeActivity)
        //when
        mPresenter.shortenUrlClicked(url)
        //then
        verify(homeActivity).showProgressDialog()
    }

    @Test
    @Throws(Exception::class)
    fun shouldHideProgressDialogAfterFetchingShortUrl() {
        //given
        val url = "www.google.com"
        _when(mDataManager.isIntroSliderShown()).thenReturn(true)
        _when(mDataManager.fetchShortUrl(ArgumentMatchers.anyString()))
                .thenReturn(Single.just(urlShortenResponse))
                .thenReturn(Single.error<UrlShortenResponse>(IOException("Error in network connection")))
        mPresenter.onAttach(homeActivity)
        //when
        mPresenter.shortenUrlClicked(url)
            //Testing progressbar hiding in case of error
        mPresenter.shortenUrlClicked(url)
        //then
        verify(homeActivity, atLeast(2)).hideProgressDialog()
    }

    @Test
    @Throws(Exception::class)
    fun shouldShowSnackbarWhenIOErrorFetchingFromApi() {
        //given
        _when(mDataManager.isIntroSliderShown()).thenReturn(true)
        _when(mDataManager.fetchShortUrl(ArgumentMatchers.anyString()))
                .thenReturn(Single.error<UrlShortenResponse>(IOException("Error in network connection")))
        val url = "www.google.com"
        mPresenter.onAttach(homeActivity)
        //when
        mPresenter.shortenUrlClicked(url)
        //then
        verify(homeActivity).showSnackbar(R.string.error_in_network_connection)
    }

    @Test
    @Throws(Exception::class)
    fun shouldOpenDialogAfterApiResponse() {
        //given
        val url = "www.google.com"
        _when(mDataManager.isIntroSliderShown()).thenReturn(true)
        _when(mDataManager.fetchShortUrl(url)).thenReturn(Single.just<UrlShortenResponse>(urlShortenResponse))
        mPresenter.onAttach(homeActivity)
        //when
        mPresenter.shortenUrlClicked(url)
        //then
        verify(homeActivity).openDialog(urlShortenResponse.id)
    }

    @Test
    @Throws(Exception::class)
    fun shouldSaveInDatabaseAfterApiResponse() {
        //Given
        val url = "www.google.com"
        _when(mDataManager.fetchShortUrl(url)).thenReturn(Single.just<UrlShortenResponse>(urlShortenResponse))
        //when
        mPresenter.shortenUrlClicked(url)
        //then
        verify(mDataManager).insertShortUrl(capture(captor))
    }

    @Test
    fun shouldOpenDialogWhenItemClickEventReceived() {
        //Given
        val shortUrl = mock(ShortUrl::class.java)
        _when(shortUrl.shortUrl).thenReturn("abc")
        mPresenter.onAttach(homeActivity)
        //when
        mPresenter.shortUrlItemClicked(shortUrl)
        //then
        verify(homeActivity).openDialog("abc")
    }

    @Test
    fun shouldLoadDataFromDatabase() {
        //given
        val shortUrlList = mock(List::class.java)
        _when(mDataManager.fetchShortUrlsFromDatabase())
                .thenReturn(Flowable.just<List<ShortUrl>>(shortUrlList as List<ShortUrl>))
        //when
        mPresenter.loadUrlsHistoryFromDatabase()
        //then
        verify(mDataManager).fetchShortUrlsFromDatabase()
    }

    @Test
    fun shouldPopulateViewsWhenDataReceived() {
        //given
        mPresenter.onAttach(homeActivity)
        val shortUrlList = mock(List::class.java) as List<ShortUrl>
        _when(mDataManager.fetchShortUrlsFromDatabase())
                .thenReturn(Flowable.just<List<ShortUrl>>(shortUrlList))
        //when
        mPresenter.loadUrlsHistoryFromDatabase()
        //then
        verify(homeActivity).populateListWithUrlHistory(shortUrlList)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        mPresenter.onDetach()
    }
}