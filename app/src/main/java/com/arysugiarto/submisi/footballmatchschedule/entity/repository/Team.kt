package com.arysugiarto.submisi.footballmatchschedule.entity.repository

import com.arysugiarto.submisi.footballmatchschedule.entity.TeamResponseDetail
import io.reactivex.Flowable

interface Team {
    fun getTeams(id : String = "0") : Flowable<TeamResponseDetail>

    fun getTeamsDetail(id : String = "0") : Flowable<TeamResponseDetail>

    fun getTeamBySearch(query: String) : Flowable<TeamResponseDetail>

    fun getAllTeam(id: String) : Flowable<TeamResponseDetail>

}