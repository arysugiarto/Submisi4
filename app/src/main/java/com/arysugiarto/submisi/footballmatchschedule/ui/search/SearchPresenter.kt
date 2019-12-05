package com.arysugiarto.submisi.footballmatchschedule.ui.search

import android.util.Log
import com.arysugiarto.submisi.footballmatchschedule.entity.repository.Repository
import com.arysugiarto.submisi.footballmatchschedule.utils.SchedulerProvider

import io.reactivex.disposables.CompositeDisposable
import java.lang.Exception

class SearchPresenter(
    val view: SearchView.View,
    val repository: Repository,
    val scheduler: SchedulerProvider
    ): SearchView.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun Cari(query: String?) {
        view.showLoading()
        compositeDisposable.add(repository.searchMatches(query)
            .observeOn(scheduler.ui())
            .subscribeOn(scheduler.io())
                .subscribe {
                    view.hideLoading()
                    try {
                        view.showMatch(it.events)
                    } catch (e: Exception) {
                        Log.e("ERROR", "Pertandingan tidak ditemukan")
//                        Toast.makeText(context,"Pertandingan tidak ditemukan",Toast.LENGTH_SHORT).show()
                        view.noData()
                    }

                })
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }


}