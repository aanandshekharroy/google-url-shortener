package com.example.theseus.urlshortener.ui.di.modules

import com.example.theseus.urlshortener.data.DataManager
import com.example.theseus.urlshortener.data.IDataManager
import dagger.Module
import dagger.Provides
import org.mockito.Mockito.mock
import javax.inject.Singleton

@Module
class TestApplicationModule {
    @Provides
    @Singleton
    fun dataManager():IDataManager = mock(DataManager::class.java)
}