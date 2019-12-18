package com.arysugiarto.submisi.footballmatchschedule.ui.detail.teamdetailactivity

import com.arysugiarto.submisi.footballmatchschedule.entity.db.DbFavoriteTeam


interface DetailTeamView {

    interface View{
        fun setFavoriteState(favList:List<DbFavoriteTeam>)
    }

    interface Presenter{
        fun deleteTeam(id:String)
        fun checkTeam(id:String)
        fun insertTeam(id: String, imgUrl: String)
    }
}