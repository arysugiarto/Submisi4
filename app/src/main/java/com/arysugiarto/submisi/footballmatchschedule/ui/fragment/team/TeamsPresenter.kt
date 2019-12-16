package com.rahmat.app.footballclub.feature.team


import com.arysugiarto.submisi.footballmatchschedule.entity.TeamResponseDetail
import com.arysugiarto.submisi.footballmatchschedule.entity.repository.TeamRepositoryImpl
import com.arysugiarto.submisi.footballmatchschedule.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

/**
 * Created by muhrahmatullah on 10/09/18.
 */
class TeamsPresenter(val mView : TeamsContract.View, val teamRepositoryImpl: TeamRepositoryImpl,
                     val scheduler: SchedulerProvider
): TeamsContract.Presenter {


    override fun searchTeam(teamName: String) {
//        mView.showLoading()
        compositeDisposable.add(teamRepositoryImpl.getTeamBySearch(teamName)
                .observeOn(scheduler.ui())
                .subscribeOn(scheduler.io())
                .subscribeWith(object: ResourceSubscriber<TeamResponseDetail>(){
                    override fun onComplete() {
                        mView.hideLoading()
                    }

                    override fun onNext(t: TeamResponseDetail) {
                        mView.displayTeams(t.teams ?: Collections.emptyList())
                    }

                    override fun onError(t: Throwable?) {
                        mView.displayTeams(Collections.emptyList())
                        mView.hideLoading()
                    }

                })
        )
    }

    val compositeDisposable = CompositeDisposable()
    override fun getTeamData(leagueName: String) {
        mView.showLoading()
        compositeDisposable.add(teamRepositoryImpl.getAllTeam(leagueName)
                .observeOn(scheduler.ui())
                .subscribeOn(scheduler.io())
                .subscribeWith(object: ResourceSubscriber<TeamResponseDetail>(){
                    override fun onComplete() {
                        mView.hideLoading()
                    }

                    override fun onNext(t: TeamResponseDetail) {
                        mView.displayTeams(t.teams ?: Collections.emptyList())
                    }

                    override fun onError(t: Throwable?) {
                        mView.displayTeams(Collections.emptyList())
                        mView.hideLoading()
                    }

                })
        )
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }

}