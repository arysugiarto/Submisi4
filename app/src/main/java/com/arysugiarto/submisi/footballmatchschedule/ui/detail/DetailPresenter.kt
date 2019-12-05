package com.arysugiarto.submisi.footballmatchschedule.ui.detail


import com.arysugiarto.submisi.footballmatchschedule.entity.repository.RepoFavorite
import com.arysugiarto.submisi.footballmatchschedule.entity.repository.Repository
import com.arysugiarto.submisi.footballmatchschedule.utils.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailPresenter(val view : DetailContract.View,
                      val repository: Repository,
                      val repoFavorite: RepoFavorite,
                      val scheduler: SchedulerProvider
) : DetailContract.Presenter {


    override fun deleteMatch(id: String) {
        repoFavorite.deleteFavorite(id)
    }

    override fun checkMatch(id: String) {
        view.setFavoriteState(repoFavorite.favorite(id))
    }

    override fun insertMatch(eventId: String, homeId: String, awayId: String) {
        repoFavorite.insertFavorite(eventId, homeId, awayId)
    }

    override fun getTeamKandang(id: String) {
        compositeDisposable.add(repository.getTeams(id)
            .observeOn(scheduler.ui())
            .subscribeOn(scheduler.io())
            .subscribe{
//                view.logoTeamHome(it.teams)
//                view.hideLoading()
            })
    }

    val compositeDisposable = CompositeDisposable()

    override fun getTeamTandang(id:String) {
        compositeDisposable.add(repository.getTeams(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe{
                    it.teams?.get(0)?.let { it1 -> view.logoTeamAway(it1) }
                })
    }

    override fun onDestroyPresenter() {
        compositeDisposable.dispose()
    }
}