package com.example.theseus.urlshortener.ui.base

import android.app.Activity
import android.os.Bundle

open class BaseActivity:Activity(), IBaseView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


}