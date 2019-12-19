package com.arysugiarto.submisi.footballmatchschedule.entity.repository

import com.arysugiarto.submisi.footballmatchschedule.entity.Kalsemen
import com.arysugiarto.submisi.footballmatchschedule.entity.TeamResponseDetail
import io.reactivex.Flowable

interface KalRepo {
//    fun getTeams(id : String = "0") : Flowable<TeamResponseDetail>
    fun getKalsemen(id:String): Flowable<Kalsemen>
}