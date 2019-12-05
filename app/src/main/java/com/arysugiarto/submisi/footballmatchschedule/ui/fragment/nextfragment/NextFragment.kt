package com.arysugiarto.submisi.footballmatchschedule.ui.fragment.nextfragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter

import com.arysugiarto.submisi.footballmatchschedule.R
import com.arysugiarto.submisi.footballmatchschedule.adapter.MatchAdapater
import com.arysugiarto.submisi.footballmatchschedule.api.ApiService
import com.arysugiarto.submisi.footballmatchschedule.api.RestApi
import com.arysugiarto.submisi.footballmatchschedule.entity.List
import com.arysugiarto.submisi.footballmatchschedule.entity.repository.Repository
import com.arysugiarto.submisi.footballmatchschedule.extensions.hide
import com.arysugiarto.submisi.footballmatchschedule.extensions.show
import com.arysugiarto.submisi.footballmatchschedule.ui.fragment.MatchView
import com.arysugiarto.submisi.footballmatchschedule.utils.AppSchedulerProvider
import kotlinx.android.synthetic.main.fragment_next.*
import kotlinx.android.synthetic.main.fragment_next.progressbar


class NextFragment : Fragment(),
    MatchView.View {

    lateinit var presenter: NextPresenter

    lateinit var nameLiga: String

    private var ListMatch: MutableList<List> = mutableListOf()

    override fun hideLoading() {
        progressbar.hide()
        rvNext.visibility = View.VISIBLE
    }

    override fun showLoading() {
        progressbar.show()
        rvNext.visibility = View.INVISIBLE
    }

    override fun showDataMatch(listmatch: kotlin.collections.List<List>) {
        ListMatch.clear()
        ListMatch.addAll(listmatch)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvNext.layoutManager = layoutManager
        rvNext.adapter = MatchAdapater(listmatch, context)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_next, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val service = ApiService.getClient().create(RestApi::class.java)
        val request = Repository(service)
        val scheduler = AppSchedulerProvider()
        presenter =
            NextPresenter(this, request,scheduler)
        presenter.getDataMatch()

        val spinnerItems = resources.getStringArray(R.array.league_name)
        val spinnerAdapter =
            ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinnerMatch.adapter = spinnerAdapter

        spinnerMatch.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                nameLiga = spinnerMatch.selectedItem.toString()
                when (nameLiga) {
                    "Liga Inggris" -> presenter.getDataMatch("4328")
                    "Liga Francis" -> presenter.getDataMatch("4334")
                    "Liga Germany" -> presenter.getDataMatch("4331")
                    "Liga Italia" -> presenter.getDataMatch("4332")
                    "Liga Spanyol" -> presenter.getDataMatch("4335")
                    "Liga America" -> presenter.getDataMatch("4346")
                    "Liga Portugal" -> presenter.getDataMatch("4344")
                    "Liga Australia" -> presenter.getDataMatch("4356")
                    "Liga Scotlaindia" -> presenter.getDataMatch("4395")
                    "Liga English League 1" -> presenter.getDataMatch("4396")
                    else -> presenter.getDataMatch()
                }
            }
        }

    }
    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
