package com.arysugiarto.submisi.footballmatchschedule.ui.fragment.favoritepertandingan

import com.arysugiarto.submisi.footballmatchschedule.entity.repository.RepoFavorite
import com.arysugiarto.submisi.footballmatchschedule.entity.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FavPresenter (val view: FavView.View,
                    val repository: Repository,
                    val repoFavorite: RepoFavorite) : FavView.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun getFootballMatchData() {
        view.showLoading()
        val FavoriteList = repoFavorite.getMatchDb()
        var listData: MutableList<com.arysugiarto.submisi.footballmatchschedule.entity.List> =
            mutableListOf()
        for (fav in FavoriteList) {
            compositeDisposable.add(repository.getEventById(fav.idEvent)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    listData.add(it.events[0])
                    view.getFootballmatch(listData)
                    view.hideLoading()
                    view.hideSwipe()
                })
        }

        if (FavoriteList.isEmpty()) {
            view.hideLoading()
            view.hideSwipe()
            view.getFootballmatch(listData)
        }
    }

    override fun onDestroyPresenter() {
        compositeDisposable.dispose()
    }
}
