package com.example.theseus.urlshortener.ui.intro

import com.example.theseus.urlshortener.data.DataManager
import com.example.theseus.urlshortener.data.IDataManager
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class IntroPresenterTest {
    lateinit var mDataManager: IDataManager
    lateinit var mPresenter: IIntroPresenter<IIntroView>
    lateinit var mIIntroView: IIntroView
    @Before
    fun setUp() {
        mDataManager = mock(DataManager::class.java)
        mIIntroView = mock(IntroActivity::class.java)
        mPresenter = IntroPresenter(mDataManager)
        mPresenter.onAttach(mIIntroView)
    }

    @Test
    fun openHomeActivityWhenIntroSliderCompletes() {
        mPresenter.introCompleted()
        verify(mDataManager).introSliderShown()
        verify(mIIntroView).openHomeActivity()
    }
}