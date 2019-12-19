package com.arysugiarto.submisi.footballmatchschedule.ui.fragment.kalsemenfragment

import com.arysugiarto.submisi.footballmatchschedule.entity.TableX


interface KalsemenView {
    interface View{
        fun hideLoading()
        fun showLoading()
        fun showDataKalsemen(matchList: List<TableX>)
    }
    interface Presenter{
        fun getDataKalsemen(namaLiga: String = "4328")
        fun onDestroy()

    }
}