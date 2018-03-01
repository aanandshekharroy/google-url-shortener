package com.example.theseus.urlshortener.data.prefs

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferenceManager @Inject constructor(val mSharedPreference:SharedPreferences): ISharedPreferenceManager {
    override var isLoggedIn: Boolean
        get() = mSharedPreference.getBoolean(loggedInPrefName,false)
        set(value) {
            mSharedPreference.edit().putBoolean(loggedInPrefName,value).apply()
        }
    val introSliderShownPrefName = "introSliderShown"
    val loggedInPrefName = "loggedIn"
    override var introSliderShown: Boolean
        get() = mSharedPreference.getBoolean(introSliderShownPrefName,false)
        set(value) {
            mSharedPreference.edit().putBoolean(introSliderShownPrefName,value).apply()
        }



}