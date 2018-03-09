package com.example.theseus.urlshortener.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = arrayOf(ShortUrl::class), version = 1)
abstract class ShortUrlDatabase : RoomDatabase() {

    abstract fun shortUrlDao(): ShortUrlDao

    companion object {

        @Volatile private var INSTANCE: ShortUrlDatabase? = null

        fun getInstance(context: Context): ShortUrlDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE
                            ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context,
                        ShortUrlDatabase::class.java, "ShortUrl.db")
                        .build()
    }
}
