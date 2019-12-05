package com.arysugiarto.submisi.footballmatchschedule.entity.repository

import com.arysugiarto.submisi.footballmatchschedule.entity.Match
import com.arysugiarto.submisi.footballmatchschedule.entity.SearchedMatches
import com.arysugiarto.submisi.footballmatchschedule.entity.TeamResponseDetail
import io.reactivex.Flowable

interface MatchRepo {
    fun getLastMatch(id : String) : Flowable<Match>

    fun getTeams(id : String = "0") : Flowable<TeamResponseDetail>

    fun getNextMatch(id : String) : Flowable<Match>

    fun searchMatches(query: String?) : Flowable<SearchedMatches>
    fun getEventById(id: String) : Flowable<Match>
}