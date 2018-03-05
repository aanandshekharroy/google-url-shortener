package com.example.theseus.urlshortener.ui.home

import com.example.theseus.urlshortener.R
import com.example.theseus.urlshortener.data.IDataManager
import com.example.theseus.urlshortener.data.model.response.UrlShortenResponse
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import junitparams.JUnitParamsRunner
import junitparams.Parameters
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.Mockito.*
import java.io.IOException
import org.mockito.Mockito.`when` as _when


@RunWith(JUnitParamsRunner::class)
class HomePresenterTest{
    @Mock
    lateinit var mDataManager:IDataManager
    @Mock
    lateinit var  homeActivity :IHomeView
    @Mock
    lateinit var mCompositeDisposable: CompositeDisposable
    val urlShortenResponse =  UrlShortenResponse("","","")

    @InjectMocks
    lateinit var  mPresenter : HomePresenter<IHomeView>
    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler {
            Schedulers.trampoline()
        }
    }


    @Test
    fun shouldOpenIntroSliderIfIntroNeverShown(){
        _when(mDataManager.isIntroSliderShown()).thenReturn(false)
        mPresenter.onAttach(homeActivity)
        verify(homeActivity).openIntroSlider()
    }

    @Test
    fun shouldNotOpenIntroWhenIntroShown(){
        _when(mDataManager.isIntroSliderShown()).thenReturn(true)
        mPresenter.onAttach(homeActivity)
        verify(homeActivity,Mockito.never()).openIntroSlider()
    }



    @Test
    @Parameters(method = "getValidUrls")
    @Throws(Exception::class)
    fun testValidSiteAddressUsingRegex(url:String){
        assertTrue(mPresenter.isValidAddress(url))
    }

    @Test
    @Parameters(method = "getInvalidUrls")
    @Throws(Exception::class)
    fun testInvalidSiteAddressUsingRegex(url:String){
        assertFalse("${url} is not invalid",mPresenter.isValidAddress(url))
    }
    fun getInvalidUrls() = arrayOf(arrayOf("//stackoverflow.com"),arrayOf("ww..google.com"))
    fun getValidUrls() = arrayOf(arrayOf("www.google.com"),arrayOf("blog.bufferapp.com/"))

    @Test
    @Parameters(method = "getInvalidUrls")
    @Throws(Exception::class)
    fun shouldShowSnackbarIfAddressInvalid(url:String){
        mPresenter.onAttach(homeActivity)
        mPresenter.shortenUrlClicked(url)
        verify(homeActivity).showSnackbar(R.string.invalid_url)
    }

    @Test
    @Throws(Exception::class)
    fun shouldShowProgressDialogWhileFetchingShortUrl(){
        val url = "www.google.com"
        _when(mDataManager.isIntroSliderShown()).thenReturn(true)
        _when(mDataManager.fetchShortUrl(ArgumentMatchers.anyString()))
                .thenReturn(Single.just(urlShortenResponse))
        mPresenter.onAttach(homeActivity)
        mPresenter.shortenUrlClicked(url)
        verify(homeActivity).showProgressDialog()
    }

    @Test
    @Throws(Exception::class)
    fun shouldHideProgressDialogAfterFetchingShortUrl(){
        val url = "www.google.com"
        _when(mDataManager.isIntroSliderShown()).thenReturn(true)
        _when(mDataManager.fetchShortUrl(ArgumentMatchers.anyString()))
                .thenReturn(Single.just(urlShortenResponse))
                .thenReturn(Single.error<UrlShortenResponse>(IOException("Error in network connection")))
        mPresenter.onAttach(homeActivity)
        mPresenter.shortenUrlClicked(url)


        //Testing progressbar hiding in case of error
        mPresenter.shortenUrlClicked(url)
        verify(homeActivity, atLeast(2)).hideProgressDialog()
    }

    @Test
    @Throws(Exception::class)
    fun shouldShowSnackbarWhenErrorFetchingFromApi(){
        _when(mDataManager.isIntroSliderShown()).thenReturn(true)
        _when(mDataManager.fetchShortUrl(ArgumentMatchers.anyString()))
                .thenReturn(Single.error<UrlShortenResponse>(IOException("Error in network connection")))
        val url = "www.google.com"
        mPresenter.onAttach(homeActivity)
        mPresenter.shortenUrlClicked(url)
        verify(homeActivity).showSnackbar(ArgumentMatchers.anyString())
    }

    @Test
    fun shouldOpenDialogAfterApiResponse(){
        val url = "www.google.com"
        RxAndroidPlugins.setInitMainThreadSchedulerHandler {
            Schedulers.trampoline()
        }
        _when(mDataManager.isIntroSliderShown()).thenReturn(true)
        _when(mDataManager.fetchShortUrl(url))
                .thenReturn(Single.just<UrlShortenResponse>(urlShortenResponse))

        mPresenter.onAttach(homeActivity)
        mPresenter.shortenUrlClicked(url)
        verify(homeActivity).openDialog(urlShortenResponse.id!!)
    }







}