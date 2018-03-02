package com.example.theseus.urlshortener.ui.home

import com.example.theseus.urlshortener.ui.base.IBaseViewPresenter

interface IHomePresenter<V:IHomeView>:IBaseViewPresenter<V> {
    fun shortenUrlClicked(longUrl: String)
}