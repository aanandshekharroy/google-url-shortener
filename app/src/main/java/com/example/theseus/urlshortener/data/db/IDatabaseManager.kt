package com.example.theseus.urlshortener.data.db

interface IDatabaseManager {
    fun insertShortUrl(shortUrl: ShortUrl)
}