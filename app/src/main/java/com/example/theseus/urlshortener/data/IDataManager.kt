package com.example.theseus.urlshortener.data

interface IDataManager {
    fun isLoggedIn(): Boolean
    fun isIntroSliderShown():Boolean
    fun introSliderShown()
}