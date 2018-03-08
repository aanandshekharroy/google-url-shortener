package com.example.theseus.urlshortener.db

import io.reactivex.Flowable

interface IDatabaseManager {
    fun insertShortUrl(shortUrl: ShortUrl): Long
    fun getShortUrlsList(): Flowable<List<ShortUrl>>
}