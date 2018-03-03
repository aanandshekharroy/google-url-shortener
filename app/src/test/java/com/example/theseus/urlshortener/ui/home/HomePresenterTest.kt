package com.example.theseus.urlshortener.ui.home

import com.example.theseus.urlshortener.R
import com.example.theseus.urlshortener.data.DataManager
import com.example.theseus.urlshortener.data.IDataManager
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import junitparams.JUnitParamsRunner
import junitparams.Parameters
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.Mockito.`when` as _when
@RunWith(JUnitParamsRunner::class)
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



    @Test
    @Parameters("http://stackoverflow.com"
            ,"www.google.com"
            ,"blog.bufferapp.com/")
    @Throws(Exception::class)
    fun testValidSiteAddressUsingRegex(url:String){
        assertTrue(mPresenter.isValidAddress(url))
    }

    @Test
    @Parameters(method = "getInvalidUrls")
    @Throws(Exception::class)
    fun testInvalidSiteAddressUsingRegex(url:String){
        assertFalse("${url} is not invalid",mPresenter.isValidAddress(url))
    }
    fun getInvalidUrls() = arrayOf(arrayOf("//stackoverflow.com"),arrayOf("ww..google.com"))
    fun getValidUrls() = arrayOf(arrayOf("www.google.com"),arrayOf("blog.bufferapp.com/"))

    @Test
    @Parameters(method = "getInvalidUrls")
    @Throws(Exception::class)
    fun shouldShowSnackbarIfAddressInvalid(url:String){
        mPresenter.onAttach(homeActivity)
        mPresenter.shortenUrlClicked(url)
        verify(homeActivity).showSnackbar(R.string.invalid_url)
    }

    @Test
    @Parameters(method = "getValidUrls")
    @Throws(Exception::class)
    fun shouldFetchShortUrlFromGoogleApi(url:String){
        mPresenter.shortenUrlClicked(url)
        verify(mDataManager).fetchShortUrl(url)
    }



}