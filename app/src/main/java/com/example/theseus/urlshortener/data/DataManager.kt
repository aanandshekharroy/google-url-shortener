package com.example.theseus.urlshortener.data

import com.example.theseus.urlshortener.data.api.IApiManager
import com.example.theseus.urlshortener.data.prefs.ISharedPreferenceManager
import com.example.theseus.urlshortener.db.IDatabaseManager
import com.example.theseus.urlshortener.db.ShortUrl
import javax.inject.Inject

class DataManager @Inject constructor( val mPreferenceManager: ISharedPreferenceManager,
    val mApiManager: IApiManager,
    val mDatabaseManager: IDatabaseManager) : IDataManager {
    override fun fetchShortUrlsFromDatabase() = mDatabaseManager.getShortUrlsList()

    override fun insertShortUrl(shortUrl: ShortUrl) = mDatabaseManager.insertShortUrl(shortUrl)

    override fun fetchShortUrl(url: String) = mApiManager.fetchShortUrl(url)
    override fun introSliderShown() {
        mPreferenceManager.introSliderShown = true
    }

    override fun isIntroSliderShown() = mPreferenceManager.introSliderShown

    override fun isLoggedIn() = mPreferenceManager.isLoggedIn
}