package com.arysugiarto.submisi.footballmatchschedule.ui.fragment

import com.arysugiarto.submisi.footballmatchschedule.entity.List


interface MatchView {
    interface View{
        fun hideLoading()
        fun showLoading()
        fun showDataMatch(matchList: kotlin.collections.List<List>)
    }
    interface Presenter{
        fun getDataMatch(namaLiga: String = "4328")
        fun onDestroy()

    }
}