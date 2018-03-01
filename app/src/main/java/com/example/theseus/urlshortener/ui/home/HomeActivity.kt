package com.example.theseus.urlshortener.ui.home

import android.os.Bundle
import com.example.theseus.urlshortener.R
import com.example.theseus.urlshortener.UrlShortenerApplication
import com.example.theseus.urlshortener.ui.base.BaseActivity
import javax.inject.Inject

class HomeActivity : BaseActivity(),IHomeView {

    @Inject
    lateinit var mPresenter: IHomePresenter<IHomeView>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        (application as UrlShortenerApplication).homeActivityComponent.inject(this)
    }
}
