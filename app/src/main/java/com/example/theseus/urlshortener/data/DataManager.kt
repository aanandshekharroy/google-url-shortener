package com.example.theseus.urlshortener.data

import com.example.theseus.urlshortener.data.api.IApiManager
import com.example.theseus.urlshortener.data.prefs.ISharedPreferenceManager
import javax.inject.Inject

class DataManager @Inject constructor( val mPreferenceManager: ISharedPreferenceManager, val mApiManager: IApiManager) : IDataManager {
    override fun fetchShortUrl(url: String) = mApiManager.fetchShortUrl(url)
    override fun introSliderShown() {
        mPreferenceManager.introSliderShown = true
    }

    override fun isIntroSliderShown() = mPreferenceManager.introSliderShown

    override fun isLoggedIn() = mPreferenceManager.isLoggedIn
}