package com.example.theseus.urlshortener.ui.intro

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.theseus.urlshortener.R
import com.example.theseus.urlshortener.UrlShortenerApplication
import com.github.paolorotolo.appintro.AppIntro
import com.github.paolorotolo.appintro.AppIntroFragment
import javax.inject.Inject

class IntroActivity : AppIntro(),IIntroView {
    override fun showProgressDialog() {


    }

    override fun hideProgressDialog() {

    }


    @Inject
    lateinit var mPresenter: IIntroPresenter<IIntroView>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setContentView(R.layout.activity_main)
//        addSlide(IntroFragment1())
//        addSlide(IntroFragment2())
//        addSlide(IntroFragment3())
//        addSlide(IntroFragment4())
        addSlide(AppIntroFragment.newInstance("ABS", "ss", R.drawable.leak_canary_icon
                , resources.getColor(R.color.material_blue_grey_800)))
        addSlide(AppIntroFragment.newInstance("ABS", "ss",R.drawable.leak_canary_icon
                , resources.getColor(R.color.material_blue_grey_800)))
        addSlide(AppIntroFragment.newInstance("ABS", "ss",R.drawable.leak_canary_icon
                , resources.getColor(R.color.material_blue_grey_800)))
        setBarColor(Color.parseColor("#3F51B5"));
        setSeparatorColor(Color.parseColor("#2196F3"));
        showSkipButton(false);
//        setProgressButtonEnabled(false);
        (application as UrlShortenerApplication).introActivityComponent.inject(this)
        mPresenter.onAttach(this)
    }

    override fun onSkipPressed(currentFragment: Fragment) {
        super.onSkipPressed(currentFragment)
        // Do something when users tap on Skip button.
    }

    override fun onDonePressed(currentFragment: Fragment) {
        super.onDonePressed(currentFragment)
        // Do something when users tap on Done button.
    }
}
