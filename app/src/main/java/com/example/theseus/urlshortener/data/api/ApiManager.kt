package com.example.theseus.urlshortener.data.api

import com.example.theseus.urlshortener.data.model.UrlShortenRequest
import javax.inject.Inject

class ApiManager @Inject constructor( val mGoogleUrlShortenerApi: GoogleUrlShortenerApi ) : IApiManager {
    override fun fetchShortUrl(longUrl: String) = mGoogleUrlShortenerApi.getShortUrl(UrlShortenRequest(longUrl))
}