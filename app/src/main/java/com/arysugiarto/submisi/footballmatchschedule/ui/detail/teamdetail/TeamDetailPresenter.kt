package com.arysugiarto.submisi.footballmatchschedule.ui.detail.teamdetail

import com.arysugiarto.submisi.footballmatchschedule.entity.repository.RepoFavorite



class TeamDetailPresenter(val mView: TeamDetailContract.View,
                          val localRepositoryImpl: RepoFavorite): TeamDetailContract.Presenter {


    override fun deleteTeam(id: String) {
        localRepositoryImpl.deleteTeamData(id)
    }

//    override fun checkTeam(id: String) {
//        mView.setFavoriteState(localRepositoryImpl.checkFavTeam(id))
//    }

    override fun insertTeam(id: String, imgUrl: String) {
        localRepositoryImpl.insertTeamData(id, imgUrl)
    }


}