package com.example.theseus.urlshortener.data.api

import com.example.theseus.urlshortener.BuildConfig
import com.example.theseus.urlshortener.data.api.model.UrlShortenRequest
import com.example.theseus.urlshortener.data.api.model.response.UrlShortenResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface GoogleUrlShortenerApi {
    @POST("url?key=${BuildConfig.URL_SHORTENER_API}")
    fun getShortUrl(@Body urlShortenRequest: UrlShortenRequest): Single<UrlShortenResponse>
}