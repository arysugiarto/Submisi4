package com.arysugiarto.submisi.footballmatchschedule.ui.fragment.favoritepertandingan

import com.arysugiarto.submisi.footballmatchschedule.entity.List
import kotlin.collections.List as List1

interface FavView {
    interface View{
        fun hideLoading()
        fun showLoading()
        fun getFootballmatch(matchList: List1<List>)
        fun hideSwipe()
    }

    interface Presenter{
        fun getFootballMatchData()
        fun onDestroyPresenter()
    }
}