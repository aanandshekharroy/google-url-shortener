package com.example.theseus.urlshortener.data

import com.example.theseus.urlshortener.data.db.ShortUrl
import com.example.theseus.urlshortener.data.api.model.response.UrlShortenResponse
import io.reactivex.Single

interface IDataManager {
    fun isLoggedIn(): Boolean
    fun isIntroSliderShown(): Boolean
    fun introSliderShown()
    fun fetchShortUrl(url: String): Single<UrlShortenResponse>
    fun insertShortUrl(shortUrl: ShortUrl)
}