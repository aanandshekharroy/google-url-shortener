package com.example.theseus.urlshortener.data

import com.example.theseus.urlshortener.data.model.response.UrlShortenResponse
import io.reactivex.Single

interface IDataManager {
    fun isLoggedIn(): Boolean
    fun isIntroSliderShown():Boolean
    fun introSliderShown()
    fun fetchShortUrl(url: String): Single<UrlShortenResponse>
}