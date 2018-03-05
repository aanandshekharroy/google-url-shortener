package com.example.theseus.urlshortener.ui.home

import android.app.ProgressDialog
import android.os.Bundle
import com.example.theseus.urlshortener.R
import com.example.theseus.urlshortener.UrlShortenerApplication
import com.example.theseus.urlshortener.ui.base.BaseActivity
import com.example.theseus.urlshortener.ui.intro.IntroActivity
import com.example.theseus.urlshortener.ui.login.LoginActivity
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class HomeActivity : BaseActivity(),IHomeView {
    var dialog: ProgressDialog? =null

    override fun openDialog(shortUrl: String) {

    }
    override fun showProgressDialog() {
        if(dialog==null){
            dialog = indeterminateProgressDialog(getString(R.string.please_wait))
        }else{
            dialog!!.show()
        }
    }

    override fun hideProgressDialog() {
        dialog?.hide()
    }
    override fun showSnackbar(string: String) {
        snackbar(content,string)
    }

    override fun showSnackbar(stringId: Int) {
        snackbar(content,stringId)
    }

    override fun openIntroSlider() {
        startActivity(intentFor<IntroActivity>().newTask())
        finish()
    }

    override fun openLoginActivity() {
        startActivity(intentFor<LoginActivity>().newTask())
        finish()
    }

    @Inject
    lateinit var mPresenter: IHomePresenter<IHomeView>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        (application as UrlShortenerApplication).homeActivityComponent.inject(this)
        mPresenter.onAttach(this)
        setupViews()
    }

    private fun setupViews() {
       RxTextView.textChanges(url_text)
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe {
           if(it.length>0){
               shorten_url.isEnabled = true
           }else{
               shorten_url.isEnabled = false
           }
       }


        RxView.clicks(shorten_url).debounce(200,TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
            mPresenter.shortenUrlClicked(url_text.text.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDetach()
    }
}
