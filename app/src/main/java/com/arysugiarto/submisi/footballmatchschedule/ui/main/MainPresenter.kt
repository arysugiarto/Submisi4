package com.arysugiarto.submisi.footballmatchschedule.ui.main

import com.arysugiarto.submisi.footballmatchschedule.entity.repository.Repository
import io.reactivex.disposables.CompositeDisposable

class MainPresenter(val mView : MainView.View, val repository: Repository) : MainView.Presenter{

    val compositeDisposable = CompositeDisposable()

}