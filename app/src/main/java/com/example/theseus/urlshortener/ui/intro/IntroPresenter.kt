package com.example.theseus.urlshortener.ui.intro

import com.example.theseus.urlshortener.data.IDataManager
import com.example.theseus.urlshortener.ui.base.BasePresenter
import javax.inject.Inject

class IntroPresenter<V:IIntroView>
@Inject constructor(val mDataManager:IDataManager):BasePresenter<V>(),IIntroPresenter<V> {
    override fun introCompleted() {
        mDataManager.introSliderShown()
        view?.openHomeActivity()
    }
}