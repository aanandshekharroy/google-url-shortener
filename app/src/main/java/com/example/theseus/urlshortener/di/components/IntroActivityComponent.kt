package com.example.theseus.urlshortener.di.components

import com.example.theseus.urlshortener.di.modules.IntroActivityModule
import com.example.theseus.urlshortener.ui.intro.IntroActivity
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(IntroActivityModule::class))
interface IntroActivityComponent {
    fun inject(introActivity: IntroActivity)
}