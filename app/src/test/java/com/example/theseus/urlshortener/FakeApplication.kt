package com.example.theseus.urlshortener

import com.example.theseus.urlshortener.di.components.ApplicationComponent
import org.mockito.Mockito.mock

class FakeApplication:UrlShortenerApplication() {
    var applicationComponent: ApplicationComponent =  mock(ApplicationComponent::class.java)


    override fun isUnitTesting() = true
}