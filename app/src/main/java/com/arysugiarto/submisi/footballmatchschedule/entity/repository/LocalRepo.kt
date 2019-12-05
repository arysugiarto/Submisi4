package com.arysugiarto.submisi.footballmatchschedule.entity.repository

import com.arysugiarto.submisi.footballmatchschedule.entity.db.DbFavoriteMatch

interface LocalRepo {
    fun getMatchDb() : List<DbFavoriteMatch>

    fun insertFavorite(eventId: String, homeId: String, awayId: String)

    fun deleteFavorite(eventId: String)

    fun favorite(eventId: String) : List<DbFavoriteMatch>
}