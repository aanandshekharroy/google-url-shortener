package com.example.theseus.urlshortener

import android.app.Application
import com.example.theseus.urlshortener.di.components.ApplicationComponent
import com.example.theseus.urlshortener.di.components.DaggerApplicationComponent
//import com.example.theseus.urlshortener.di.components.DaggerApplicationComponent
import com.example.theseus.urlshortener.di.components.IntroActivityComponent
import com.example.theseus.urlshortener.di.modules.ApplicationModule
import com.example.theseus.urlshortener.di.modules.IntroActivityModule
import com.squareup.leakcanary.LeakCanary

class UrlShortenerApplication :Application(){
    lateinit var mApplicationComponent: ApplicationComponent

    val introActivityComponent : IntroActivityComponent by lazy {
        mApplicationComponent.introActivityComponent(IntroActivityModule())
    }

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this)
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this)).build()
    }


}