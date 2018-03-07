package com.example.theseus.urlshortener.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable
@Dao
interface ShortUrlDao {
    @Query("SELECT * FROM ShortUrls WHERE id =:id")
    fun getShortUrlById(id: Int): Flowable<ShortUrl>

    @Query("SELECT * FROM ShortUrls ORDER BY createdAt DESC")
    fun getListOfShortUrls(): Flowable<List<ShortUrl>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShortUrl(shortUrl: ShortUrl)

    @Delete
    fun deleteShortUrl(shortUrl: ShortUrl)
}