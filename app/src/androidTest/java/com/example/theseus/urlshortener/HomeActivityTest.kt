package com.example.theseus.urlshortener

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.closeSoftKeyboard
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.theseus.urlshortener.data.IDataManager
import com.example.theseus.urlshortener.ui.home.HomeActivity
import com.example.theseus.urlshortener.ui.home.IHomePresenter
import com.example.theseus.urlshortener.ui.home.IHomeView
import io.reactivex.disposables.CompositeDisposable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when` as _when

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {
    @get:Rule
    var mActivityRule = ActivityTestRule<HomeActivity>(HomeActivity::class.java)
    @Mock
    lateinit var mDataManager: IDataManager
    @Mock
    lateinit var mCompositeDisposable: CompositeDisposable
    @InjectMocks
    lateinit var mPresenter: IHomePresenter<*>
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        _when(mDataManager.isIntroSliderShown()).thenReturn(true)
        mActivityRule.activity.mPresenter = mPresenter as IHomePresenter<IHomeView>
    }

    @Test
    fun shouldShowSnackbar_whenInvalidUrl() {
        //given
        onView(withId(R.id.url_text))
                .perform(typeText("INVALID URL"), closeSoftKeyboard())
        //when
        onView(withId(R.id.shorten_url)).perform(click())
        //then - check snackbar sppears on invalid irls sometimes
        onView(withText(mActivityRule.activity.getString(R.string.invalid_url)))
                .check(matches(withEffectiveVisibility(
                        ViewMatchers.Visibility.VISIBLE
                )))
    }

    @Test
    fun shouldShowProgressBar_whileFetchingUrl() {
        //given
        onView(withId(R.id.url_text))
                .perform(typeText("www.google.com"), closeSoftKeyboard())
        //when
        onView(withId(R.id.shorten_url)).perform(click())
        //then - check progress dialog appears
        onView(withText(mActivityRule.activity.getString(R.string.please_wait)))
                .check(matches(withEffectiveVisibility(
                        ViewMatchers.Visibility.VISIBLE
                )))
    }
}