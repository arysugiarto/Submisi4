package com.arysugiarto.submisi.footballmatchschedule.entity.repository

import com.arysugiarto.submisi.footballmatchschedule.entity.db.DbFavoriteMatch
import com.arysugiarto.submisi.footballmatchschedule.entity.db.FavoriteTeam

interface LocalRepo {
    fun getMatchDb() : List<DbFavoriteMatch>

    fun insertFavorite(eventId: String, homeId: String, awayId: String)

    fun deleteFavorite(eventId: String)

    fun favorite(eventId: String) : List<DbFavoriteMatch>


    fun getTeamFromDb() : List<FavoriteTeam>

    fun insertTeamData(teamId: String, imgUrl: String)

    fun deleteTeamData(teamId: String)

    fun checkFavTeam(teamId: String) : List<FavoriteTeam>
}