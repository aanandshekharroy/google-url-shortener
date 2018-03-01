package com.example.theseus.urlshortener.di.modules

import com.example.theseus.urlshortener.ui.intro.IIntroPresenter
import com.example.theseus.urlshortener.ui.intro.IIntroView
import com.example.theseus.urlshortener.ui.intro.IntroPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class IntroActivityModule {
    @Provides

    fun introActivityPresenter(mPresenter: IntroPresenter<IIntroView>)
            :IIntroPresenter<IIntroView> = mPresenter
}