package com.arysugiarto.submisi.footballmatchschedule.entity.repository

import com.arysugiarto.submisi.footballmatchschedule.api.RestApi
import com.arysugiarto.submisi.footballmatchschedule.entity.Kalsemen
import com.arysugiarto.submisi.footballmatchschedule.entity.TeamResponseDetail
import io.reactivex.Flowable

class KalsemenRepository (private val restApi: RestApi) : KalRepo{
//    override fun getTeams(id: String): Flowable<TeamResponseDetail> = restApi.getTeam(id)

    override fun getKalsemen(id: String): Flowable<Kalsemen> = restApi.getKalsemen(id)
}