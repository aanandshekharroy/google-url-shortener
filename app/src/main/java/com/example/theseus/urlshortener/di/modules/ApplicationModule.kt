package com.example.theseus.urlshortener.di.modules

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.example.theseus.urlshortener.data.DataManager
import com.example.theseus.urlshortener.data.IDataManager
import com.example.theseus.urlshortener.data.api.ApiManager
import com.example.theseus.urlshortener.data.api.IApiManager
import com.example.theseus.urlshortener.data.prefs.ISharedPreferenceManager
import com.example.theseus.urlshortener.data.prefs.SharedPreferenceManager
import com.example.theseus.urlshortener.data.db.DatabaseManager
import com.example.theseus.urlshortener.data.db.IDatabaseManager
import com.example.theseus.urlshortener.data.db.ShortUrlDao
import com.example.theseus.urlshortener.data.db.ShortUrlDatabase
import com.example.theseus.urlshortener.di.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule (val context: Application) {

    @Provides
    @Singleton
    @ApplicationContext
    fun applicationContext(): Application = context

    @Provides
    @Singleton
    fun dataManager(dataManager: DataManager): IDataManager = dataManager

    @Provides
    @Singleton
    fun preferenceManager(preferenceManager: SharedPreferenceManager): ISharedPreferenceManager = preferenceManager

    @Provides
    @Singleton
    fun apiManager(apiManager: ApiManager): IApiManager = apiManager

    @Provides
    @Singleton
    fun databaseManager(databaseManager: DatabaseManager): IDatabaseManager = databaseManager

    @Provides
    @Singleton
    fun sharedPreference(): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    @Singleton
    fun provideShortUrlDataSource(): ShortUrlDao {
        val database = ShortUrlDatabase.getInstance(context)
        return database.shortUrlDao()
    }
}