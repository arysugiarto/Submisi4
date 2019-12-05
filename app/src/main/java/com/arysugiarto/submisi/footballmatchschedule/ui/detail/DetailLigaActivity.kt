package com.arysugiarto.submisi.footballmatchschedule.ui.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.arysugiarto.submisi.footballmatchschedule.R
import com.arysugiarto.submisi.footballmatchschedule.api.ApiService
import com.arysugiarto.submisi.footballmatchschedule.entity.Leagues
import com.arysugiarto.submisi.footballmatchschedule.preft.SharedPref
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_liga.*

class DetailLigaActivity : AppCompatActivity(),LigaView {
    var id: String = ""
    private lateinit var presenter: DetailLigaPresenter
    private lateinit var sharedpref: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_liga)

        val apiService = ApiService()
        presenter = DetailLigaPresenter(this, apiService)
        id = intent.getStringExtra("id_liga")
        sharedpref = SharedPref(this)
        sharedpref.setIdLiga(id)
        presenter.getDetailLiga(id)
    }

    override fun showLiga(liga: Leagues) {
        Picasso.get().load(liga.strFanart1).into(img)
        tvLiga.text = liga.strLeague
        tvDesc.text = liga.strDescriptionEN
    }
}
