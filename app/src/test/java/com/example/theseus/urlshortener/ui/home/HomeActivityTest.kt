package com.example.theseus.urlshortener.ui.home

import android.content.Intent
import android.widget.Button
import android.widget.EditText
import com.example.theseus.urlshortener.BuildConfig
import com.example.theseus.urlshortener.FakeApplication
import com.example.theseus.urlshortener.R
import com.example.theseus.urlshortener.ui.intro.IntroActivity
import com.example.theseus.urlshortener.ui.login.LoginActivity
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config



@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, application = FakeApplication::class)
class HomeActivityTest {
    lateinit var homeActivity: HomeActivity
    lateinit var mPresenter:IHomePresenter<*>
    @Before
    fun setUp() {
        homeActivity = Robolectric.buildActivity(HomeActivity::class.java).create().get()
        mPresenter = mock(HomePresenter::class.java)
        homeActivity.mPresenter = mPresenter as IHomePresenter<IHomeView>
    }
    @Test
    fun shouldLaunchLoginActivity(){
        homeActivity.openLoginActivity()
        val expectedIntent = Intent(homeActivity, LoginActivity::class.java)
        val shadowActivity = Shadows.shadowOf(homeActivity)
        val actualIntent = shadowActivity.getNextStartedActivity()
        assertTrue(actualIntent.filterEquals(expectedIntent))
    }
    @Test
    fun shouldLaunchIntroActivity(){
        homeActivity.openIntroSlider()
        val expectedIntent = Intent(homeActivity, IntroActivity::class.java)
        val shadowActivity = Shadows.shadowOf(homeActivity)
        val actualIntent = shadowActivity.getNextStartedActivity()
        assertTrue(actualIntent.filterEquals(expectedIntent))
    }
    @Test
    fun shouldCallPresenterOnShortenLinkButtonClicked(){
        val longUrl = homeActivity.findViewById<EditText>(R.id.url_text).text.toString()
        homeActivity.findViewById<Button>(R.id.shorten_url).performClick()
        verify(mPresenter).shortenUrlClicked(longUrl)
    }
}