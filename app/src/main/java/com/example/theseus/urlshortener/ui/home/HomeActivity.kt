package com.example.theseus.urlshortener.ui.home

import android.app.AlertDialog
import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.TextView
import com.example.theseus.urlshortener.R
import com.example.theseus.urlshortener.UrlShortenerApplication
import com.example.theseus.urlshortener.copyText
import com.example.theseus.urlshortener.db.ShortUrl
import com.example.theseus.urlshortener.di.modules.HomeActivityModule
import com.example.theseus.urlshortener.ui.base.BaseActivity
import com.example.theseus.urlshortener.ui.intro.IntroActivity
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_home.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.jetbrains.anko.share
import org.jetbrains.anko.toast
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class HomeActivity : BaseActivity(), IHomeView {
    override fun populateListWithUrlHistory(shortUrlList: List<ShortUrl>) {
        mAdapter.mShortUrls = shortUrlList
    }

    @Inject
    lateinit var progressDialog: ProgressDialog
    @Inject
    lateinit var alertDialog: AlertDialog
    @Inject
    lateinit var mAdapter: ShortUrlAdapter
    override fun openDialog(shortUrl: String) {
        alertDialog.setTitle(shortUrl)
        alertDialog.show()
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onItemClicked(shortUrl: ShortUrl) {
        mPresenter.shortUrlItemClicked(shortUrl)
    }
    override fun showProgressDialog() {
        progressDialog.show()
    }

    override fun hideProgressDialog() {
        progressDialog.hide()
    }
    override fun showSnackbar(string: String) {
        snackbar(content, string)
    }

    override fun showSnackbar(stringId: Int) {
        snackbar(content, stringId)
    }

    override fun openIntroSlider() {
        startActivity(intentFor<IntroActivity>().newTask())
        finish()
    }

    @Inject
    lateinit var mPresenter: IHomePresenter<IHomeView>
    @Inject
    lateinit var mCompositeDisposable: CompositeDisposable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        (application as UrlShortenerApplication).mApplicationComponent
                .homeActivityComponent(HomeActivityModule(this))
                .inject(this)
        mPresenter.onAttach(this)
        setupViews()
    }

    private fun setupViews() {
        mCompositeDisposable.add(
                RxTextView.textChanges(url_text)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            shorten_url.isEnabled = it.isNotEmpty()
                }
        )

        mCompositeDisposable.add(RxView.clicks(shorten_url).debounce(200, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                mPresenter.shortenUrlClicked(url_text.text.toString())
            }
        )

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.copy)) {
            dialog, which ->
            copyText(this, getAlertDialogTitle())
            toast(getString(R.string.url_copied))
        }
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.share)) {
            dialog, which ->
            share(getAlertDialogTitle())
        }
        shortUrlList.layoutManager = LinearLayoutManager(this)
        shortUrlList.adapter = mAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDetach()
        mCompositeDisposable.dispose()
    }

    fun getAlertDialogTitle(): String {
        val titleId = resources.getIdentifier("alertTitle", "id", "android")
        if (titleId > 0) {
            val dialogTitle = alertDialog.findViewById(titleId) as TextView
            return dialogTitle.text.toString()
        }
        return ""
    }
}
