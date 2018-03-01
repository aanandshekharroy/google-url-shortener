package com.example.theseus.urlshortener.data

import com.example.theseus.urlshortener.data.prefs.ISharedPreferenceManager
import javax.inject.Inject

class DataManager @Inject constructor( val mPreferenceManager: ISharedPreferenceManager):IDataManager {
    override fun introSliderShown() {
        mPreferenceManager.introSliderShown = true
    }

    override fun isIntroSliderShown() = mPreferenceManager.introSliderShown

    override fun isLoggedIn() = mPreferenceManager.isLoggedIn
}