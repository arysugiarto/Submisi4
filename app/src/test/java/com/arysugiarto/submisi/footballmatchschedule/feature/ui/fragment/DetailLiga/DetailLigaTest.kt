package com.arysugiarto.submisi.footballmatchschedule.feature.ui.fragment.DetailLiga

import com.arysugiarto.submisi.footballmatchschedule.api.ApiService
import com.arysugiarto.submisi.footballmatchschedule.api.RestApi
import com.arysugiarto.submisi.footballmatchschedule.entity.LeagueDetailRespon
import com.arysugiarto.submisi.footballmatchschedule.entity.Leagues
import com.arysugiarto.submisi.footballmatchschedule.ui.detail.DetailLigaPresenter
import com.arysugiarto.submisi.footballmatchschedule.ui.detail.LigaView
import com.nhaarman.mockito_kotlin.argumentCaptor
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailLigaTest {
    @Mock
    private lateinit var view: LigaView
    @Mock
    private lateinit var apiRepository: ApiService

    @Mock
    private lateinit var presenter: DetailLigaPresenter
    @Mock
    private lateinit var leagueResponse: Leagues

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        apiRepository = ApiService()
        presenter = DetailLigaPresenter(view, apiRepository)
    }


    @Test
    fun getDetailLiga() {
        val id = "4328"
        presenter.getDetailLiga(id)
        val connect : RestApi = apiRepository.getUrl().create(RestApi::class.java)
        argumentCaptor<LigaView>().apply {
            connect.getDetailLeugue(id).enqueue(object : Callback<LeagueDetailRespon> {
                override fun onFailure(call: Call<LeagueDetailRespon>, t: Throwable) {
                    Mockito.verify(t.message)
                }

                override fun onResponse(call: Call<LeagueDetailRespon>, response: Response<LeagueDetailRespon>) {
                    val get: Leagues = response.body()!!.leagues.get(0)
                    firstValue.showLiga(get)
                    Mockito.verify(view.showLiga(get))
                }

            })
        }

    }
}