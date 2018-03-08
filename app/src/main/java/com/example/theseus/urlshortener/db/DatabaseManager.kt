package com.example.theseus.urlshortener.db

import javax.inject.Inject

class DatabaseManager @Inject constructor(val mShortUrlDao: ShortUrlDao) : IDatabaseManager {
    override fun getShortUrlsList() = mShortUrlDao.getListOfShortUrls()

    override fun insertShortUrl(shortUrl: ShortUrl) = mShortUrlDao.insertShortUrl(shortUrl)
}