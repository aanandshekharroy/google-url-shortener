package com.example.theseus.urlshortener.di.modules

import com.example.theseus.urlshortener.di.scope.HomeActivityScope
import com.example.theseus.urlshortener.ui.home.HomePresenter
import com.example.theseus.urlshortener.ui.home.IHomePresenter
import com.example.theseus.urlshortener.ui.home.IHomeView
import dagger.Module
import dagger.Provides

@Module
class HomeActivityModule {
    @Provides
    @HomeActivityScope
    fun presenter(presenter:HomePresenter<IHomeView>):IHomePresenter<IHomeView> = presenter
}