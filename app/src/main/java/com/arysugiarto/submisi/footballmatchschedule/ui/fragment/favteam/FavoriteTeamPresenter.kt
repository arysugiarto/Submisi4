package com.arysugiarto.submisi.footballmatchschedule.ui.fragment.favteam

import com.arysugiarto.submisi.footballmatchschedule.entity.Team
import com.arysugiarto.submisi.footballmatchschedule.entity.TeamResponseDetail
import com.arysugiarto.submisi.footballmatchschedule.entity.repository.RepoFavorite
import com.arysugiarto.submisi.footballmatchschedule.entity.repository.TeamRepositoryImpl
import com.arysugiarto.submisi.footballmatchschedule.utils.SchedulerProvider
import com.rahmat.app.footballclub.feature.favteam.FavoriteTeamContract
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*


class FavoriteTeamPresenter(val mView: FavoriteTeamContract.View,
                            val localRepositoryImpl: RepoFavorite,
                            val teamRepositoryImpl: TeamRepositoryImpl,
                            val scheduler: SchedulerProvider
): FavoriteTeamContract.Presenter {


    val compositeDisposable = CompositeDisposable()

    override fun getTeamData() {
        mView.showLoading()
        val teamList = localRepositoryImpl.getTeamFromDb()
        var teamLists: MutableList<Team> = mutableListOf()
        for (fav in teamList){
            compositeDisposable.add(teamRepositoryImpl.getTeamsDetail(fav.idTeam)
                    .observeOn(scheduler.ui())
                    .subscribeOn(scheduler.io())
                    .subscribeWith(object: ResourceSubscriber<TeamResponseDetail>(){
                        override fun onComplete() {
                            mView.hideLoading()
                            mView.hideSwipeRefresh()
                        }

                        override fun onNext(t: TeamResponseDetail) {
                            teamLists.add(t.teams[0])
                            mView.displayTeams(teamLists)
                        }

                        override fun onError(t: Throwable?) {
                            mView.hideLoading()
                            mView.hideSwipeRefresh()
                            mView.displayTeams(Collections.emptyList())
                        }

                    })
            )
        }

        if(teamList.isEmpty()){
            mView.hideLoading()
            mView.hideSwipeRefresh()
            mView.displayTeams(teamLists)
        }
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}