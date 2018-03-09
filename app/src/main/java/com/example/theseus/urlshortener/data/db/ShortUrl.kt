package com.example.theseus.urlshortener.data.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "shortUrls")
data class ShortUrl (@PrimaryKey(autoGenerate = true)
                     @ColumnInfo(name = "id")
                     val id: Long = 0,
                     @ColumnInfo(name = "longUrl")
                     val longUrl: String,
                     @ColumnInfo(name = "shortUrl")
                     val shortUrl: String,
                     @ColumnInfo(name = "createdAt")
                     val createdAt: Long = System.currentTimeMillis()
                     )