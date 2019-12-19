package com.arysugiarto.submisi.footballmatchschedule.ui.fragment.teamfragment


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.LinearLayout.VERTICAL
import com.arysugiarto.submisi.footballmatchschedule.R
import com.arysugiarto.submisi.footballmatchschedule.adapter.TeamAdapter
import com.arysugiarto.submisi.footballmatchschedule.api.ApiService
import com.arysugiarto.submisi.footballmatchschedule.api.RestApi
import com.arysugiarto.submisi.footballmatchschedule.entity.Team
import com.arysugiarto.submisi.footballmatchschedule.entity.repository.TeamRepository
import com.arysugiarto.submisi.footballmatchschedule.extensions.hide
import com.arysugiarto.submisi.footballmatchschedule.extensions.show

import com.arysugiarto.submisi.footballmatchschedule.utils.AppSchedulerProvider

import kotlinx.android.synthetic.main.fragment_teams.*


class TeamsFragment : Fragment(), TeamsView.View {

    lateinit var leagueName : String
    lateinit var presenter : TeamsView.Presenter

    private var teamLists : MutableList<Team> = mutableListOf()

    override fun displayTeams(teamList: List<Team>) {
        teamLists.clear()
        teamLists.addAll(teamList)
        val layoutManager = LinearLayoutManager(context, VERTICAL,false)
        rvTeam.layoutManager = layoutManager
        rvTeam.adapter = TeamAdapter(teamLists, context)
    }

    override fun hideLoading() {
        mainProgressBar.hide()
        rvTeam.visibility = View.VISIBLE
    }

    override fun showLoading() {
        mainProgressBar.show()
        rvTeam.visibility = View.INVISIBLE
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_teams, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val service = ApiService.getClient().create(RestApi::class.java)
        val request = TeamRepository(service)
        val scheduler = AppSchedulerProvider()
        setHasOptionsMenu(true)
        presenter = TeamsPresenter(this, request, scheduler)
        presenter.getTeamData("4328")
        val spinnerItems = resources.getStringArray(R.array.league_name)
        val spinnerAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinnerTeam.adapter = spinnerAdapter
        spinnerTeam.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = spinnerTeam.selectedItem.toString()
                when(leagueName){
                    "Liga Inggris" -> presenter.getTeamData("4328")
                    "Liga Francis" -> presenter.getTeamData("4334")
                    "Liga Germany" -> presenter.getTeamData("4331")
                    "Liga Italia" -> presenter.getTeamData("4332")
                    "Liga Spanyol" -> presenter.getTeamData("4335")
                    "Liga America" -> presenter.getTeamData("4346")
                    "Liga Portugal" -> presenter.getTeamData("4344")
                    "Liga Australia" -> presenter.getTeamData("4356")
                    "Liga Scotlaindia" -> presenter.getTeamData("4395")
                    "Liga English League 1" -> presenter.getTeamData("4396")
                    else -> presenter.getTeamData("4328")
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.search, menu)
        val searchView = menu?.findItem(R.id.actionSearch)?.actionView as SearchView?
        searchView?.queryHint = "Search team"

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                presenter.searchTeam(newText)
                return false
            }
        })

        searchView?.setOnCloseListener(object: SearchView.OnCloseListener{
            override fun onClose(): Boolean {
                presenter.getTeamData("4328")
                return true
            }
        })
    }
}
