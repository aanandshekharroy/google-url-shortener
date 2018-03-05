package com.example.theseus.urlshortener.ui.base

import android.app.Activity
import android.app.ProgressDialog
import android.os.Bundle
import org.jetbrains.anko.progressDialog

open class BaseActivity:Activity(), IBaseView {
    lateinit var dialog:ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialog = progressDialog(message = "Please wait a bit…", title = "Fetching data")
    }

    override fun showProgressDialog() {
        dialog.show()
    }

    override fun hideProgressDialog() {
        dialog.hide()
    }
}