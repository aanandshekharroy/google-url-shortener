package com.example.theseus.urlshortener.ui.base

open class BasePresenter<V:IBaseView>:IBaseViewPresenter<V> {
    var view:IBaseView? = null
    override fun onAttach(v: IBaseView) {
        view = v
    }

    override fun onDetach() {
        view = null
    }
}