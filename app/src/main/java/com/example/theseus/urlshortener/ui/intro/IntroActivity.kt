package com.example.theseus.urlshortener.ui.intro

import android.os.Bundle
import com.example.theseus.urlshortener.R
import com.example.theseus.urlshortener.UrlShortenerApplication
import com.example.theseus.urlshortener.ui.base.BaseActivity
import javax.inject.Inject

class IntroActivity : BaseActivity(),IIntroView {


    @Inject
    lateinit var mPresenter: IIntroPresenter<IIntroView>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as UrlShortenerApplication).introActivityComponent.inject(this)
        mPresenter.onAttach(this)
    }
}
