package com.example.stargazer.activity

import android.os.Bundle
import com.example.stargazer.R
import com.example.stargazer.presenter.BasePresenter
import com.example.stargazer.view.BaseView
import moxy.MvpAppCompatActivity
import moxy.presenter.ProvidePresenter

abstract class BaseActivity<Presenter: BasePresenter<*>>: MvpAppCompatActivity() {
    abstract var presenter: Presenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

}