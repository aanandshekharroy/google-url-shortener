package com.example.theseus.urlshortener.ui.intro

import com.example.theseus.urlshortener.ui.base.IBaseViewPresenter

interface IIntroPresenter<V:IIntroView>:IBaseViewPresenter<V> {
    fun introCompleted()
}