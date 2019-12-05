package com.arysugiarto.submisi.footballmatchschedule.entity.repository

import android.content.Context
import com.arysugiarto.submisi.footballmatchschedule.entity.db.DbFavoriteMatch
import com.arysugiarto.submisi.footballmatchschedule.entity.db.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class RepoFavorite (private val context: Context) : LocalRepo {


    override fun favorite(eventId: String): List<DbFavoriteMatch> {
        return context.database.use {
            val result = select(DbFavoriteMatch.TB_FAVORITE)
                .whereArgs(
                    "(EVENT_ID = {id})",
                    "id" to eventId
                )
            val favorite = result.parseList(classParser<DbFavoriteMatch>())
            favorite
        }
    }


    override fun deleteFavorite(eventId: String) {
        context.database.use {
            delete(
                DbFavoriteMatch.TB_FAVORITE, "(EVENT_ID = {id})",
                "id" to eventId
            )
        }
    }

    override fun insertFavorite(eventId: String, homeId: String, awayId: String) {
        context.database.use {
            insert(
                DbFavoriteMatch.TB_FAVORITE,
                DbFavoriteMatch.EVENT_ID to eventId,
                DbFavoriteMatch.ID_TEAM_KANDANG to homeId,
                DbFavoriteMatch.ID_TEAM_TANDANG to awayId)

        }
    }

    override fun getMatchDb(): List<DbFavoriteMatch> {
        lateinit var favoriteList: List<DbFavoriteMatch>
        context.database.use {
            val result = select(DbFavoriteMatch.TB_FAVORITE)
            val favorite = result.parseList(classParser<DbFavoriteMatch>())
            favoriteList = favorite
        }
        return favoriteList
    }
}
