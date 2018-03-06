package com.example.theseus.urlshortener.ui.intro

import android.content.Intent
import android.support.v4.app.Fragment
import com.example.theseus.urlshortener.BuildConfig
import com.example.theseus.urlshortener.FakeApplication
import com.example.theseus.urlshortener.ui.home.HomeActivity
import junit.framework.Assert
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
class IntroActivityTest {
    lateinit var introActivity: IntroActivity
    lateinit var mPresenter: IIntroPresenter<*>
    @Before
    fun setUp() {
        introActivity = Robolectric.buildActivity(IntroActivity::class.java).create().get()
        mPresenter = mock(IIntroPresenter::class.java)
        introActivity.mPresenter = mPresenter as IIntroPresenter<IIntroView>
    }

    @Test
    fun testPresenterIsCalledWhenIntroCompletes() {
        introActivity.onDonePressed(mock(Fragment::class.java))
        verify(mPresenter).introCompleted()
    }

    @Test
    fun shouldLaunchHomeActivity() {
        introActivity.openHomeActivity()
        val expectedIntent = Intent(introActivity, HomeActivity::class.java)
        val shadowActivity = Shadows.shadowOf(introActivity)
        val actualIntent = shadowActivity.getNextStartedActivity()
        Assert.assertTrue(actualIntent.filterEquals(expectedIntent))
    }
}