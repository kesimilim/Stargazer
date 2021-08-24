package com.example.stargazer.presenter

import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.MvpView

@InjectViewState
abstract class BasePresenter<View: MvpView>: MvpPresenter<View>() {
}