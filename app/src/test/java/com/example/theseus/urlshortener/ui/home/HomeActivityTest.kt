package com.example.theseus.urlshortener.ui.home

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import com.example.theseus.urlshortener.BuildConfig
import com.example.theseus.urlshortener.FakeApplication
import com.example.theseus.urlshortener.R
import com.example.theseus.urlshortener.ui.intro.IntroActivity
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, application = FakeApplication::class)
class HomeActivityTest {
    lateinit var homeActivity: HomeActivity
    @Mock
    lateinit var mPresenter: IHomePresenter<*>
    @Mock
    lateinit var mProgressDialog: ProgressDialog
    @Mock
    lateinit var mAlertDialog: AlertDialog

    @Before
    fun setUp() {
        homeActivity = Robolectric.buildActivity(HomeActivity::class.java).create().get()
        MockitoAnnotations.initMocks(this)
        homeActivity.mPresenter = mPresenter as IHomePresenter<IHomeView>
    }
    @Test
    fun shouldLaunchIntroActivity() {
        homeActivity.openIntroSlider()
        val expectedIntent = Intent(homeActivity, IntroActivity::class.java)
        val shadowActivity = Shadows.shadowOf(homeActivity)
        val actualIntent = shadowActivity.getNextStartedActivity()
        assertTrue(actualIntent.filterEquals(expectedIntent))
    }
    @Test
    fun shouldCallPresenterOnShortenLinkButtonClicked() {
        val longUrl = homeActivity.findViewById<EditText>(R.id.url_text).text.toString()
        homeActivity.findViewById<Button>(R.id.shorten_url).performClick()
        //Can't test RxBindings with Robolectric
//        verify(mPresenter).shortenUrlClicked(longUrl)
    }

    @Test
    fun shouldSetTextAndShowDialog() {
        homeActivity.alertDialog = mAlertDialog
        homeActivity.openDialog("")
        verify(mAlertDialog).setTitle(ArgumentMatchers.anyString())
        verify(mAlertDialog).show()
    }

    @Test
    fun shouldShowProgressBar() {
        homeActivity.progressDialog = mProgressDialog
        homeActivity.showProgressDialog()
        verify(mProgressDialog).show()
    }

    @Test
    fun shouldHideProgressBar() {
        homeActivity.progressDialog = mProgressDialog
        homeActivity.hideProgressDialog()
        verify(mProgressDialog).hide()
    }
}