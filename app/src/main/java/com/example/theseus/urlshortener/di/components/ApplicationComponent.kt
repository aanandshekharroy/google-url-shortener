package com.example.theseus.urlshortener.di.components

import com.example.theseus.urlshortener.di.modules.ApplicationModule
import com.example.theseus.urlshortener.di.modules.HomeActivityModule
import com.example.theseus.urlshortener.di.modules.IntroActivityModule
import com.example.theseus.urlshortener.di.modules.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(ApplicationModule::class, NetworkModule::class))
@Singleton
interface ApplicationComponent {
    fun introActivityComponent(introActivityModule: IntroActivityModule): IntroActivityComponent
    fun homeActivityComponent(homeActivityModule: HomeActivityModule): HomeActivityComponent
}