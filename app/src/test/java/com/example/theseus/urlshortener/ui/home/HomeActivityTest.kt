package com.example.theseus.urlshortener.ui.home

import com.example.theseus.urlshortener.BuildConfig
import com.example.theseus.urlshortener.FakeApplication
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, application = FakeApplication::class)
class HomeActivityTest {
    lateinit var homeActivity: HomeActivity
    @Before
    fun setUp() {
        homeActivity = Robolectric.buildActivity(HomeActivity::class.java).create().get()
    }
    @Test
    fun shouldAttachPresenterInOnCreate(){

    }
}