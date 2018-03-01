package com.example.theseus.urlshortener.data.prefs

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferenceManager @Inject constructor(val mSharedPreference:SharedPreferences): ISharedPreferenceManager {
    val introSliderShownPrefName = "introSliderShown"
    override var introSliderShown: Boolean
        get() = mSharedPreference.getBoolean(introSliderShownPrefName,false)
        set(value) {
            mSharedPreference.edit().putBoolean(introSliderShownPrefName,value).apply()
        }



}