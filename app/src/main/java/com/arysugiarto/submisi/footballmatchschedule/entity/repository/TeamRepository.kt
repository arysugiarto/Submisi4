package com.rahmat.app.footballclub.entity.repository

import com.arysugiarto.submisi.footballmatchschedule.entity.TeamResponseDetail
import io.reactivex.Flowable

/**
 * Created by muhrahmatullah on 10/09/18.
 */
interface TeamRepository {

    fun getTeams(id : String = "0") : Flowable<TeamResponseDetail>

    fun getTeamsDetail(id : String = "0") : Flowable<TeamResponseDetail>

    fun getTeamBySearch(query: String) : Flowable<TeamResponseDetail>

    fun getAllTeam(id: String) : Flowable<TeamResponseDetail>
}