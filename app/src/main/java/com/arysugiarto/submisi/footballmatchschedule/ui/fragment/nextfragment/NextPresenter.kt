package com.arysugiarto.submisi.footballmatchschedule.ui.fragment.nextfragment

import com.arysugiarto.submisi.footballmatchschedule.entity.repository.Repository
import com.arysugiarto.submisi.footballmatchschedule.ui.fragment.MatchView
import com.arysugiarto.submisi.footballmatchschedule.utils.SchedulerProvider

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NextPresenter(
    val mView: MatchView.View,
    val matchRepositoryImpl: Repository,
    val scheduler: SchedulerProvider
) : MatchView.Presenter {



    override fun onDestroy() {
        compositeDisposable.dispose()
    }

    val compositeDisposable = CompositeDisposable()

    override fun getDataMatch(namaLiga: String) {
        mView.showLoading()
        compositeDisposable.add(matchRepositoryImpl.getNextMatch(namaLiga)
            .observeOn(scheduler.ui())
            .subscribeOn(scheduler.io())
            .subscribe{
                mView.showDataMatch(it.events)
                mView.hideLoading()
            })
    }

}