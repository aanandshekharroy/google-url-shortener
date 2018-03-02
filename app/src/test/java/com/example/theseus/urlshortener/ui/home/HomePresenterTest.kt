package com.example.theseus.urlshortener.ui.home

import com.example.theseus.urlshortener.data.DataManager
import com.example.theseus.urlshortener.data.IDataManager
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.Mockito.`when` as _when
class HomePresenterTest{
    lateinit var mDataManager:IDataManager
    lateinit var  homeActivity :IHomeView
    lateinit var  mPresenter : IHomePresenter<IHomeView>
    @Before
    fun setUp(){
        mDataManager = mock(DataManager::class.java)
        homeActivity = mock(HomeActivity::class.java)
        mPresenter = HomePresenter(mDataManager)

    }


    @Test
    fun shouldOpenIntroSliderIfIntroNeverShown(){
        _when(mDataManager.isIntroSliderShown()).thenReturn(false)
        mPresenter.onAttach(homeActivity)
        verify(homeActivity).openIntroSlider()
    }

    @Test
    fun shouldNotOpenIntroWhenIntroShown(){
        _when(mDataManager.isIntroSliderShown()).thenReturn(true)
        mPresenter.onAttach(homeActivity)
        verify(homeActivity,Mockito.never()).openIntroSlider()
    }


}