package com.example.theseus.urlshortener.data

import com.example.theseus.urlshortener.data.prefs.ISharedPreferenceManager
import javax.inject.Inject

class DataManager @Inject constructor( val mPreferenceManager: ISharedPreferenceManager):IDataManager {
}