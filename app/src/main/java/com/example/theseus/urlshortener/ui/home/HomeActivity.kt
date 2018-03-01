package com.example.theseus.urlshortener.ui.home

import android.os.Bundle
import com.example.theseus.urlshortener.R
import com.example.theseus.urlshortener.UrlShortenerApplication
import com.example.theseus.urlshortener.ui.base.BaseActivity
import com.example.theseus.urlshortener.ui.intro.IntroActivity
import com.example.theseus.urlshortener.ui.login.LoginActivity
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import javax.inject.Inject

class HomeActivity : BaseActivity(),IHomeView {
    override fun openIntroSlider() {
        startActivity(intentFor<IntroActivity>().newTask())
        finish()
    }

    override fun openLoginActivity() {

        startActivity(intentFor<LoginActivity>().newTask())
        finish()
    }

    @Inject
    lateinit var mPresenter: IHomePresenter<IHomeView>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        (application as UrlShortenerApplication).homeActivityComponent.inject(this)
        mPresenter.onAttach(this)
    }
}
