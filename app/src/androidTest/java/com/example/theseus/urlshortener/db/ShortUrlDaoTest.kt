package com.example.theseus.urlshortener.db

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ShortUrlDaoTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: ShortUrlDatabase
    @Before
    fun initDb() {
        // using an in-memory database because the information stored here disappears after test
        database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                ShortUrlDatabase::class.java)
                // allowing main thread queries, just for testing
                .allowMainThreadQueries()
                .build()
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun getShortUrlWhenNoUrlsPresent() {
        database.shortUrlDao().getShortUrlById(1).test().assertNoValues()
    }

    @Test
    fun insertAndGetShortUrl() {
        // When inserting a new url in the data source
        val id = database.shortUrlDao().insertShortUrl(SHORT_URL)

        // When subscribing to the emissions of the url
        database.shortUrlDao().getShortUrlById(id)
                .test()
                // assertValue asserts that there was only one emission of the url
                .assertValue { it.id == id && it.longUrl == SHORT_URL.longUrl && it.shortUrl == SHORT_URL.shortUrl }
    }

    @Test
    fun deleteAndGetShortUrls() {
        //Given we have an entry in the database
        val id = database.shortUrlDao().insertShortUrl(SHORT_URL)
        //And we delete it
        database.shortUrlDao().deleteShortUrl(SHORT_URL)
        //Then we shouldn't have any entries in the database
        database.shortUrlDao().getShortUrlById(id).test().assertNoValues()
    }

    companion object {
        private val SHORT_URL = ShortUrl(id = 10, longUrl = "www.google.com", shortUrl = "https://goo.gl")
    }
}