package com.example.theseus.urlshortener.data

import com.example.theseus.urlshortener.data.api.IApiManager
import com.example.theseus.urlshortener.data.prefs.ISharedPreferenceManager
import com.example.theseus.urlshortener.db.IDatabaseManager
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class DataManagerTest {

    @Mock
    lateinit var mApi: IApiManager
    @Mock
    lateinit var mPreferenceManager: ISharedPreferenceManager
    @Mock
    lateinit var mDatabaseManager: IDatabaseManager
    @InjectMocks
    lateinit var mDataManager: DataManager
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }
    @Test
    fun shouldFetchShortUrlFromApi() {
        mDataManager.fetchShortUrl("")
        verify(mApi).fetchShortUrl(ArgumentMatchers.anyString())
    }
}