package com.rahmat.app.footballclub.feature.team

import com.arysugiarto.submisi.footballmatchschedule.entity.Team



interface TeamsContract {
    interface View{
        fun displayTeams(teamList: List<Team>)
        fun hideLoading()
        fun showLoading()

    }
    interface Presenter{
        fun getTeamData(leagueName: String)
        fun searchTeam(teamName: String)
        fun onDestroy()
    }
}