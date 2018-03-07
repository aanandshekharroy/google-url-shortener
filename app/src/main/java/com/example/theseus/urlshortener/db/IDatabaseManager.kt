package com.example.theseus.urlshortener.db

interface IDatabaseManager {
    fun insertShortUrl(shortUrl: ShortUrl): Long
}