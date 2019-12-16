package com.rahmat.app.footballclub.feature.favteam

import com.arysugiarto.submisi.footballmatchschedule.entity.Team


interface FavoriteTeamContract {

    interface View{
        fun displayTeams(teamList: List<Team>)
        fun hideLoading()
        fun showLoading()
        fun hideSwipeRefresh()
    }

    interface Presenter{
        fun getTeamData()
        fun onDestroy()
    }
}