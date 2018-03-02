package com.example.theseus.urlshortener.ui.login

import com.example.theseus.urlshortener.BuildConfig
import com.example.theseus.urlshortener.FakeApplication
import com.example.theseus.urlshortener.ui.intro.IntroActivity
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, application = FakeApplication::class)
class LoginActivityTest {
    lateinit var loginActivity: IntroActivity
    @Before
    fun setUp() {
        loginActivity = Robolectric.buildActivity(IntroActivity::class.java).create().get()
    }


}