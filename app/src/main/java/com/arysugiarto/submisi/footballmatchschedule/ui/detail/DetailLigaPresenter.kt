package com.arysugiarto.submisi.footballmatchschedule.ui.detail

import com.arysugiarto.submisi.footballmatchschedule.api.ApiService
import com.arysugiarto.submisi.footballmatchschedule.api.RestApi
import com.arysugiarto.submisi.footballmatchschedule.entity.LeagueDetailRespon
import com.arysugiarto.submisi.footballmatchschedule.entity.Leagues
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailLigaPresenter (private var view: LigaView , private var apiService: ApiService) {
    fun getDetailLiga(id : String){

        val connect : RestApi = apiService.getUrl().create(RestApi::class.java)
        connect.getDetailLeugue(id).enqueue(object : Callback<LeagueDetailRespon> {
            override fun onFailure(call: Call<LeagueDetailRespon>, t: Throwable) {

            }

            override fun onResponse(call: Call<LeagueDetailRespon>, response: Response<LeagueDetailRespon>) {
                val getDetail : Leagues = response.body()?.leagues!!.get(0)
                view.showLiga(getDetail)

            }

        })
    }
}