package com.arysugiarto.submisi.footballmatchschedule.ui.detail.teamdetailactivity

import com.arysugiarto.submisi.footballmatchschedule.entity.repository.RepoFavorite



class DetailTeamPresenter(val view: DetailTeamView.View,
                          val repoFavorite: RepoFavorite): DetailTeamView.Presenter {


    override fun deleteTeam(id: String) {
        repoFavorite.deleteTeam(id)
    }

    override fun checkTeam(id: String) {
        view.setFavoriteState(repoFavorite.checkFav(id))
    }

    override fun insertTeam(id: String, urlImage: String) {
        repoFavorite.insertTeam(id, urlImage)
    }


}