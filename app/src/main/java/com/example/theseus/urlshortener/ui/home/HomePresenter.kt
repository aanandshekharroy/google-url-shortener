package com.example.theseus.urlshortener.ui.home

import com.example.theseus.urlshortener.R
import com.example.theseus.urlshortener.data.IDataManager
import com.example.theseus.urlshortener.db.ShortUrl
import com.example.theseus.urlshortener.ui.base.BasePresenter
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomePresenter<V : IHomeView> @Inject constructor(val mDataManager: IDataManager, val mCompositeDisposable: CompositeDisposable) : BasePresenter<V>(), IHomePresenter<V> {
    val webUrlRegex: Regex by lazy {
            """^(http://|https://)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?${'$'}""".toRegex()
    }
    override fun isValidAddress(url: String) = webUrlRegex.matches(url)
    var shortUrl = ""
    override fun shortenUrlClicked(longUrl: String) {
        if (!isValidAddress(longUrl)) {
            view?.showSnackbar(R.string.invalid_url)
        } else {
            view?.showProgressDialog()
            mCompositeDisposable.add(
                mDataManager
                    .fetchShortUrl(longUrl)
                    .map {
                        shortUrl = it.id
                        ShortUrl(longUrl = it.longUrl, shortUrl = it.id)
                    }
                    .flatMapCompletable {
                        Completable.fromAction {
                            mDataManager.insertShortUrl(it)
                        }
                    }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy (
                        onComplete = {
                            view?.openDialog(shortUrl)
                            view?.hideProgressDialog()
                        },
                        onError = {
                            view?.hideProgressDialog()
                            view?.showSnackbar(it.localizedMessage)
                        }
                    )
            )
        }
    }

    override fun onAttach(v: V) {
        super.onAttach(v)
        if (!mDataManager.isIntroSliderShown()) {
            view?.openIntroSlider()
        }
    }

    override fun onDetach() {
        super.onDetach()
        mCompositeDisposable.dispose()
    }
}