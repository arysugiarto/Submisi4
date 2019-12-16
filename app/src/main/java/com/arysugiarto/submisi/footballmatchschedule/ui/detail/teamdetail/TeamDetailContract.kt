package com.arysugiarto.submisi.footballmatchschedule.ui.detail.teamdetail

import com.arysugiarto.submisi.footballmatchschedule.entity.db.FavoriteTeam


interface TeamDetailContract {

    interface View{
        fun setFavoriteState(favList:List<FavoriteTeam>)
    }

    interface Presenter{
        fun deleteTeam(id:String)
//        fun checkTeam(id:String)
        fun insertTeam(id: String, imgUrl: String)
    }
}