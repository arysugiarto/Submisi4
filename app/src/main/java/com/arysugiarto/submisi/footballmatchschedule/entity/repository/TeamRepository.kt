package com.arysugiarto.submisi.footballmatchschedule.entity.repository

import com.arysugiarto.submisi.footballmatchschedule.api.RestApi
import com.arysugiarto.submisi.footballmatchschedule.entity.TeamResponseDetail
import io.reactivex.Flowable

class TeamRepository(val resApi: RestApi) : Team {

    override fun getAllTeam(id: String) = resApi.getAllTeam(id)
    override fun getTeamBySearch(query: String) = resApi.getTeamBySearch(query)
    override fun getTeams(id: String): Flowable<TeamResponseDetail> = resApi.getAllTeam(id)
    override fun getTeamsDetail(id: String): Flowable<TeamResponseDetail> = resApi.getTeam(id)

}