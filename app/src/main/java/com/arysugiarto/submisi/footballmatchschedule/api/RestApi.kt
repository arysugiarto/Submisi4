package com.arysugiarto.submisi.footballmatchschedule.api

import com.arysugiarto.submisi.footballmatchschedule.entity.*
import io.reactivex.Flowable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {
    @GET("eventspastleague.php")
    fun getLastmatch(@Query("id") id:String) : Flowable<Match>

    @GET("eventsnextleague.php")
    fun getNextMatch(@Query("id") id:String) : Flowable<Match>

    @GET("lookupteam.php")
    fun getTeam(@Query("id") id:String) : Flowable<TeamResponseDetail>

    @GET("searchevents.php")
    fun searchMatches(@Query("e") query: String?) : Flowable<SearchedMatches>

    @GET("api/v1/json/1/lookupleague.php")
    fun getDetailLeugue(@Query("id") id_league : String) : Call<LeagueDetailRespon>


    @GET("lookupevent.php")
    fun getEventById(@Query("id") id:String) : Flowable<Match>



    @GET("searchteams.php")
    fun getTeamBySearch(@Query("t") query: String) : Flowable<TeamResponseDetail>

    @GET("lookup_all_teams.php")
    fun getAllTeam(@Query("id") id:String) : Flowable<TeamResponseDetail>



}