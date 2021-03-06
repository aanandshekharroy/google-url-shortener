package com.example.theseus.urlshortener.ui.intro

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.theseus.urlshortener.R
import com.example.theseus.urlshortener.UrlShortenerApplication
import com.example.theseus.urlshortener.ui.home.HomeActivity
import com.github.paolorotolo.appintro.AppIntro
import com.github.paolorotolo.appintro.AppIntroFragment
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import javax.inject.Inject

class IntroActivity : AppIntro(), IIntroView {

    @Inject
    lateinit var mPresenter: IIntroPresenter<IIntroView>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addSlide(AppIntroFragment.newInstance("ABS", "ss", R.drawable.sign_in_button
                , resources.getColor(R.color.material_blue_grey_800)))
        addSlide(AppIntroFragment.newInstance("ABS", "ss", R.drawable.sign_in_button
                , resources.getColor(R.color.material_blue_grey_800)))
        addSlide(AppIntroFragment.newInstance("ABS", "ss", R.drawable.sign_in_button
                , resources.getColor(R.color.material_blue_grey_800)))
        setBarColor(Color.parseColor("#3F51B5"))
        setSeparatorColor(Color.parseColor("#2196F3"))
        showSkipButton(false)
        (application as UrlShortenerApplication).introActivityComponent.inject(this)
        mPresenter.onAttach(this)
    }

    override fun onDonePressed(currentFragment: Fragment) {
        super.onDonePressed(currentFragment)
        mPresenter.introCompleted()
    }
    override fun openHomeActivity() {

        startActivity(intentFor<HomeActivity>().newTask())
        finish()
    }
    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDetach()
    }
}
