package com.arysugiarto.submisi.footballmatchschedule.entity.db


class DbFavoriteTeam(
        val id: Long?,
        val idTeam: String,
        val urlImage: String
){
    companion object {
        const val TEAM_TABLE: String = "TABLE_TEAM"
        const val ID: String = "ID_"
        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_IMAGE: String = "TEAM_IMAGE"
    }
}