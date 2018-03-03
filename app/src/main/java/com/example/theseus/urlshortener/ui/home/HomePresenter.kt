package com.example.theseus.urlshortener.ui.home

import com.example.theseus.urlshortener.R
import com.example.theseus.urlshortener.data.IDataManager
import com.example.theseus.urlshortener.ui.base.BasePresenter
import javax.inject.Inject

class HomePresenter<V:IHomeView> @Inject constructor(val mDataManager: IDataManager):BasePresenter<V>(),IHomePresenter<V> {
    val webUrlRegex :Regex by lazy {
            """^(http://|https://)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?${'$'}""".toRegex()
        }
    override fun isValidAddress(url: String) = webUrlRegex.matches(url)

    override fun shortenUrlClicked(longUrl: String) {
        if(!isValidAddress(longUrl)){
            view?.showSnackbar(R.string.invalid_url)
        }
    }

    override fun onAttach(v: V) {
        super.onAttach(v)
        if(!mDataManager.isIntroSliderShown()){
            view?.openIntroSlider()
        }
    }


}