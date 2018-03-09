package com.example.theseus.urlshortener.ui.home

import android.support.annotation.StringRes
import com.example.theseus.urlshortener.data.db.ShortUrl
import com.example.theseus.urlshortener.ui.base.IBaseView

interface IHomeView : IBaseView {
    fun openIntroSlider()
    fun showSnackbar(@StringRes stringId: Int)
    fun showSnackbar(string: String)
    fun openDialog(shortUrl: String)
    fun showProgressDialog()
    fun hideProgressDialog()
    fun populateListWithUrlHistory(shortUrlList: List<ShortUrl>)
}