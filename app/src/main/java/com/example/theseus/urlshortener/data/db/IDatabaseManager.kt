package com.example.theseus.urlshortener.data.db

import io.reactivex.Flowable

interface IDatabaseManager {
    fun insertShortUrl(shortUrl: ShortUrl): Long
    fun getShortUrlsList(): Flowable<List<ShortUrl>>
}