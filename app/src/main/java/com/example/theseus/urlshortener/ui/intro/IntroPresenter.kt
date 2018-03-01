package com.example.theseus.urlshortener.ui.intro

import com.example.theseus.urlshortener.data.IDataManager
import com.example.theseus.urlshortener.ui.base.BasePresenter
import com.example.theseus.urlshortener.ui.base.IBaseView
import javax.inject.Inject

class IntroPresenter<V:IIntroView>
@Inject constructor(val mDataManager:IDataManager):BasePresenter<V>(),IIntroPresenter<V> {
    override fun onAttach(v: IBaseView) {
        super.onAttach(v)
//        if(mDataManager.isIntroSliderShown()){
//
//        }else{
//
//        }
    }
}