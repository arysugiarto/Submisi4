package com.arysugiarto.submisi.footballmatchschedule.ui.fragment.teamfragment


import com.arysugiarto.submisi.footballmatchschedule.entity.TeamResponseDetail
import com.arysugiarto.submisi.footballmatchschedule.entity.repository.TeamRepository
import com.arysugiarto.submisi.footballmatchschedule.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*


class TeamsPresenter(val mView : TeamsView.View, val teamRepository: TeamRepository,
                     val scheduler: SchedulerProvider
): TeamsView.Presenter {


    override fun searchTeam(teamName: String) {
//        mView.showLoading()
        compositeDisposable.add(teamRepository.getTeamBySearch(teamName)
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
        compositeDisposable.add(teamRepository.getAllTeam(leagueName)
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