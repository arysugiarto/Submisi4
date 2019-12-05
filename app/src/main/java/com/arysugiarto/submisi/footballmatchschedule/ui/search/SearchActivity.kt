package com.arysugiarto.submisi.footballmatchschedule.ui.search

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import com.arysugiarto.submisi.footballmatchschedule.R
import com.arysugiarto.submisi.footballmatchschedule.adapter.MatchAdapater
import com.arysugiarto.submisi.footballmatchschedule.api.ApiService
import com.arysugiarto.submisi.footballmatchschedule.api.RestApi
import com.arysugiarto.submisi.footballmatchschedule.entity.repository.Repository
import com.arysugiarto.submisi.footballmatchschedule.extensions.hide
import com.arysugiarto.submisi.footballmatchschedule.extensions.show
import com.arysugiarto.submisi.footballmatchschedule.utils.AppSchedulerProvider

import kotlinx.android.synthetic.main.activity_search.*


class SearchActivity : AppCompatActivity(), com.arysugiarto.submisi.footballmatchschedule.ui.search.SearchView.View{
    override fun noData() {
        progressbar.hide()
        rvPertandingan.hide()
        no_data.show()
    }


    private var ListPertandingan : MutableList<com.arysugiarto.submisi.footballmatchschedule.entity.List> = mutableListOf()
    lateinit var presenter: com.arysugiarto.submisi.footballmatchschedule.ui.search.SearchView.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        val query = intent.getStringExtra("query")
        val service = ApiService.getClient().create(RestApi::class.java)
        val request = Repository(service)
        val scheduler = AppSchedulerProvider()
        presenter = SearchPresenter(this,  request,scheduler)
        presenter.Cari(query)
    }

    override fun showLoading() {
        progressbar.show()
        rvPertandingan.hide()
        no_data.hide()
    }

    override fun hideLoading() {
        progressbar.hide()
        rvPertandingan.show()
        no_data.hide()
    }

    override fun showMatch(matchList: List<com.arysugiarto.submisi.footballmatchschedule.entity.List>) {
        ListPertandingan.clear()
        ListPertandingan.addAll(matchList)
        val filtered = ListPertandingan.filter { it.strSport.equals("Soccer") }
        filtered.forEach {
            val layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            rvPertandingan.layoutManager = layoutManager
            rvPertandingan.adapter = MatchAdapater(filtered, applicationContext)
        }
//        ListPertandingan!!.forEach { event ->
//            if (event.strSport.equals("Soccer")) {
//                val e = event
//                matchSoccer.addAll(e)
//                //.................
//            }
//        }


//
//        Log.e("MATCH SOCCER", matchSoccer.toString())


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search, menu)
        val cariPertandingan = menu?.findItem(R.id.actionSearch)?.actionView as SearchView?
        cariPertandingan?.queryHint = "Cari Pertandingan"
        cariPertandingan?.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }
            override fun onQueryTextChange(text: String?): Boolean {
                presenter.Cari(text)
                return false
            }
        })
        return true
    }



}

