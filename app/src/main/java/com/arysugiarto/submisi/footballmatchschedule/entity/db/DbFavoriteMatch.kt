package com.arysugiarto.submisi.footballmatchschedule.entity.db

class DbFavoriteMatch (val id: Long?, val idEvent: String, val idTeamKandang: String, val idTeamTandang: String) {
    companion object{
        const val TB_FAVORITE: String = "TB_FAVORITE"
        const val ID: String = "ID_"
        const val EVENT_ID: String = "EVENT_ID"
        const val ID_TEAM_KANDANG: String = "TEAM_KANDANG"
        const val ID_TEAM_TANDANG: String = "TEAM_TANDANG"
    }
}