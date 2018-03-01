package com.example.theseus.urlshortener.di.components

import com.example.theseus.urlshortener.di.modules.HomeActivityModule
import com.example.theseus.urlshortener.di.scope.HomeActivityScope
import com.example.theseus.urlshortener.ui.home.HomeActivity
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(HomeActivityModule::class))
@HomeActivityScope
interface HomeActivityComponent {
    fun inject(homeActivity: HomeActivity)
}