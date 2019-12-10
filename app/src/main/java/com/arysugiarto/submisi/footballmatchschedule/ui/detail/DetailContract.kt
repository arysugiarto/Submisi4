package com.arysugiarto.submisi.footballmatchschedule.ui.detail
import com.arysugiarto.submisi.footballmatchschedule.entity.Team
import com.arysugiarto.submisi.footballmatchschedule.entity.db.DbFavoriteMatch


interface DetailContract {
    interface View{
        fun logoTeamHome(team: Team)
        fun logoTeamAway(team: Team)
        fun setFavoriteState(favList:List<DbFavoriteMatch>)
        fun hideLoading()
        fun showLoading()
        fun getTeamKandang(id:String)
    }

    interface Presenter{
        fun getTeamKandang(id:String)
        fun getTeamTandang(id:String)
        fun deleteMatch(id:String)
        fun checkMatch(id:String)
        fun insertMatch(eventId: String, homeId: String, awayId: String)
        fun onDestroyPresenter()
    }
}