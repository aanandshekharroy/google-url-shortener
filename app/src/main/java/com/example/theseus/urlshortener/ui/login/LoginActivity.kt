package com.example.theseus.urlshortener.ui.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.theseus.urlshortener.R
import com.example.theseus.urlshortener.ui.intro.IntroActivity
import org.jetbrains.anko.startActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        startActivity<IntroActivity>()
    }
}
