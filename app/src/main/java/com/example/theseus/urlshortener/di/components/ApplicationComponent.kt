package com.example.theseus.urlshortener.di.components

import com.example.theseus.urlshortener.di.modules.ApplicationModule
import com.example.theseus.urlshortener.di.modules.IntroActivityModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(ApplicationModule::class))
@Singleton
interface ApplicationComponent {
    fun introActivityComponent(introActivityModule: IntroActivityModule):IntroActivityComponent
}