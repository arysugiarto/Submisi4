package com.arysugiarto.submisi.footballmatchschedule.entity.repository

import com.arysugiarto.submisi.footballmatchschedule.api.RestApi
import com.arysugiarto.submisi.footballmatchschedule.entity.TeamResponseDetail
import com.rahmat.app.footballclub.entity.repository.TeamRepository
import io.reactivex.Flowable

class TeamRepositoryImpl(val footballRest: RestApi) : TeamRepository {

    override fun getAllTeam(id: String) = footballRest.getAllTeam(id)
    override fun getTeamBySearch(query: String) = footballRest.getTeamBySearch(query)
    override fun getTeams(id: String): Flowable<TeamResponseDetail> = footballRest.getAllTeam(id)
    override fun getTeamsDetail(id: String): Flowable<TeamResponseDetail> = footballRest.getTeam(id)

}