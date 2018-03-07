package com.example.theseus.urlshortener.data.db

import javax.inject.Inject

class DatabaseManager @Inject constructor(val mShortUrlDao: ShortUrlDao) : IDatabaseManager {
    override fun insertShortUrl(shortUrl: ShortUrl) = mShortUrlDao.insertShortUrl(shortUrl)
}