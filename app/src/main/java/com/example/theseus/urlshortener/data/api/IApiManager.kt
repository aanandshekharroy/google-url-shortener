package com.example.theseus.urlshortener.data.api

import com.example.theseus.urlshortener.data.model.response.UrlShortenResponse
import io.reactivex.Single

interface IApiManager {
    fun fetchShortUrl(longUrl: String): Single<UrlShortenResponse>
}