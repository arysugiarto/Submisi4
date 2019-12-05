package com.arysugiarto.submisi.footballmatchschedule.ui.search
import com.arysugiarto.submisi.footballmatchschedule.entity.List

interface SearchView {

    interface View{
        fun showLoading()
        fun hideLoading()
        fun showMatch(matchList: kotlin.collections.List<List>)
        fun noData()
    }
    interface Presenter{
        fun Cari(query: String?)
        fun onDestroy()
    }

}