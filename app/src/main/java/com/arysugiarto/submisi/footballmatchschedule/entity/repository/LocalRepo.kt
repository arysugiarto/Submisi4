package com.arysugiarto.submisi.footballmatchschedule.entity.repository

import com.arysugiarto.submisi.footballmatchschedule.entity.db.DbFavoriteMatch
import com.arysugiarto.submisi.footballmatchschedule.entity.db.DbFavoriteTeam

interface LocalRepo {
    fun getMatchDb() : List<DbFavoriteMatch>

    fun insertFavorite(eventId: String, homeId: String, awayId: String)

    fun deleteFavorite(eventId: String)

    fun favorite(eventId: String) : List<DbFavoriteMatch>


    fun getTeamData() : List<DbFavoriteTeam>

    fun insertTeam(teamId: String, imgUrl: String)

    fun deleteTeam(teamId: String)

    fun checkFav(teamId: String) : List<DbFavoriteTeam>
}