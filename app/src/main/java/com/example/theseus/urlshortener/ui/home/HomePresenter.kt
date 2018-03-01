package com.example.theseus.urlshortener.ui.home

import com.example.theseus.urlshortener.data.IDataManager
import com.example.theseus.urlshortener.ui.base.BasePresenter
import javax.inject.Inject

class HomePresenter<V:IHomeView> @Inject constructor(val mDataManager: IDataManager):BasePresenter<V>(),IHomePresenter<V> {
    override fun onAttach(v: V) {
        super.onAttach(v)

        if(!mDataManager.isLoggedIn()){
            if(mDataManager.isIntroSliderShown()){
                view?.openLoginActivity()
            }else{
                view?.openIntroSlider()
            }

        }
    }


}