package com.example.theseus.urlshortener.ui.base

interface IBaseViewPresenter<V: IBaseView> {
    fun onAttach(v: IBaseView)
    fun onDetach()
}