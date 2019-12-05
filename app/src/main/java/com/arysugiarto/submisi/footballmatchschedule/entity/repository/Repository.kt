package com.arysugiarto.submisi.footballmatchschedule.entity.repository

import com.arysugiarto.submisi.footballmatchschedule.api.RestApi
import com.arysugiarto.submisi.footballmatchschedule.entity.Match
import com.arysugiarto.submisi.footballmatchschedule.entity.TeamResponseDetail
import io.reactivex.Flowable

class Repository (private val restApi: RestApi) : MatchRepo {
    override fun searchMatches(query: String?) = restApi.searchMatches(query)

    override fun getNextMatch(id: String): Flowable<Match> = restApi.getNextMatch(id)

    override fun getLastMatch(id: String): Flowable<Match> = restApi.getLastmatch(id)

    override fun getTeams(id: String): Flowable<TeamResponseDetail> = restApi.getTeam(id)

    override fun getEventById(id: String): Flowable<Match> = restApi.getEventById(id)
}