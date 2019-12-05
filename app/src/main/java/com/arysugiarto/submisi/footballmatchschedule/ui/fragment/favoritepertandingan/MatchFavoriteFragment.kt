package com.arysugiarto.submisi.footballmatchschedule.ui.fragment.favoritepertandingan


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.arysugiarto.submisi.footballmatchschedule.R
import com.arysugiarto.submisi.footballmatchschedule.adapter.MatchAdapater
import com.arysugiarto.submisi.footballmatchschedule.api.ApiService
import com.arysugiarto.submisi.footballmatchschedule.api.RestApi
import com.arysugiarto.submisi.footballmatchschedule.entity.repository.RepoFavorite
import com.arysugiarto.submisi.footballmatchschedule.entity.repository.Repository
import com.arysugiarto.submisi.footballmatchschedule.extensions.hide
import com.arysugiarto.submisi.footballmatchschedule.extensions.show
import kotlinx.android.synthetic.main.fragment_match_favorite.*

/**
 * A simple [Fragment] subclass.
 */
class MatchFavoriteFragment : Fragment(), FavView.View {


    private var listMatchs : MutableList<com.arysugiarto.submisi.footballmatchschedule.entity.List> = mutableListOf()
    lateinit var presenter: FavPresenter

    override fun hideLoading() {
        progressbar.hide()
        rvFavorite.visibility = View.VISIBLE
    }

    override fun showLoading() {
        progressbar.show()
        rvFavorite.visibility = View.INVISIBLE
    }

    override fun getFootballmatch(listMatch: List<com.arysugiarto.submisi.footballmatchschedule.entity.List>) {
        listMatchs.clear()
        listMatchs.addAll(listMatch)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvFavorite.layoutManager = layoutManager
        rvFavorite.adapter = MatchAdapater(listMatch, context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val service = ApiService.getClient().create(RestApi::class.java)
        val request = Repository(service)
        val localRepository = RepoFavorite(context!!)
        presenter = FavPresenter(this, request, localRepository)
        progressbar.show()
        presenter.getFootballMatchData()
        swiferefresh.setOnRefreshListener {
            presenter.getFootballMatchData()
        }


    }

    override fun hideSwipe() {
        swiferefresh.isRefreshing = false
        progressbar.hide()
        rvFavorite.visibility = View.VISIBLE
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_match_favorite, container, false)
        presenter.getFootballMatchData()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroyPresenter()
    }

}

