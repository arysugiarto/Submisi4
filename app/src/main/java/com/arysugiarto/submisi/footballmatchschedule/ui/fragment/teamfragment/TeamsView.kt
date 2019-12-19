package com.arysugiarto.submisi.footballmatchschedule.ui.fragment.teamfragment

import com.arysugiarto.submisi.footballmatchschedule.entity.Team



interface TeamsView {
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