package com.example.theseus.urlshortener.di.modules

import com.example.theseus.urlshortener.data.api.GoogleUrlShortenerApi
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun retrofitInstance() = Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/urlshortener/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    @Provides
    @Singleton
    fun googleUrlShortenerService(retrofit: Retrofit) =
            retrofit.create(GoogleUrlShortenerApi::class.java)
}