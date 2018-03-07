package com.example.theseus.urlshortener.di.modules

import android.app.AlertDialog
import android.content.Context
import com.example.theseus.urlshortener.R
import com.example.theseus.urlshortener.di.ActivityContext
import com.example.theseus.urlshortener.di.scope.HomeActivityScope
import com.example.theseus.urlshortener.ui.home.HomePresenter
import com.example.theseus.urlshortener.ui.home.IHomePresenter
import com.example.theseus.urlshortener.ui.home.IHomeView
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.alert
import org.jetbrains.anko.indeterminateProgressDialog

@Module
class HomeActivityModule (val context: Context) {

    @Provides
    @HomeActivityScope
    @ActivityContext
    fun context() = context

    @Provides
    @HomeActivityScope
    fun presenter(presenter: HomePresenter<IHomeView>): IHomePresenter<IHomeView> = presenter

    @Provides
    @HomeActivityScope
    fun compositeDisposable() = CompositeDisposable()

    @Provides
    @HomeActivityScope
    fun progressDialog() = context.indeterminateProgressDialog(context.getString(R.string.please_wait)).apply {
        hide()
    }
    @Provides
    @HomeActivityScope
    fun alertDialog(): AlertDialog = context.alert("", "") {
    }.build()
}