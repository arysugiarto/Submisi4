package com.arysugiarto.submisi.footballmatchschedule.ui.fragment.lastfragment
import com.arysugiarto.submisi.footballmatchschedule.entity.repository.Repository
import com.arysugiarto.submisi.footballmatchschedule.ui.fragment.MatchView
import com.arysugiarto.submisi.footballmatchschedule.utils.SchedulerProvider

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class LastPresenter(
    val view: MatchView.View,
    val repository : Repository,
    val scheduler: SchedulerProvider
) : MatchView.Presenter {


    val compositeDisposable = CompositeDisposable()

    override fun getDataMatch(namaLiga: String) {
        view.showLoading()
        compositeDisposable.add(repository.getLastMatch(namaLiga)
            .observeOn(scheduler.ui())
            .subscribeOn(scheduler.io())
            .subscribe{
                view.showDataMatch(it.events)
                view.hideLoading()
            })
    }
    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}