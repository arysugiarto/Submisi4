package com.arysugiarto.submisi.footballmatchschedule.ui.fragment.kalsemenfragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter

import com.arysugiarto.submisi.footballmatchschedule.R
import com.arysugiarto.submisi.footballmatchschedule.adapter.KalsemenAdapter
import com.arysugiarto.submisi.footballmatchschedule.api.ApiService
import com.arysugiarto.submisi.footballmatchschedule.api.RestApi
import com.arysugiarto.submisi.footballmatchschedule.entity.TableX
import com.arysugiarto.submisi.footballmatchschedule.entity.repository.KalsemenRepository
import com.arysugiarto.submisi.footballmatchschedule.extensions.hide
import com.arysugiarto.submisi.footballmatchschedule.extensions.show
import com.arysugiarto.submisi.footballmatchschedule.utils.AppSchedulerProvider
import kotlinx.android.synthetic.main.fragment_kalsemen.*

/**
 * A simple [Fragment] subclass.
 */
class KalsemenFragment : Fragment(),KalsemenView.View {

    lateinit var presenter: KalsemenPresenter

    lateinit var nameLiga: String

    private var ListKalsemen: MutableList<TableX> = mutableListOf()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val service = ApiService.getClient().create(RestApi::class.java)
        val request = KalsemenRepository(service)
        val scheduler = AppSchedulerProvider()
        presenter = KalsemenPresenter(this, request,scheduler)
        presenter.getDataKalsemen()
        val spinnerItems = resources.getStringArray(R.array.league_name)
        val spinnerAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinnerKal.adapter = spinnerAdapter

        spinnerKal.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                nameLiga = spinnerKal.selectedItem.toString()
                when (nameLiga) {
                    "Liga Inggris" -> presenter.getDataKalsemen("4328")
                    "Liga Francis" -> presenter.getDataKalsemen("4334")
                    "Liga Germany" -> presenter.getDataKalsemen("4331")
                    "Liga Italia" -> presenter.getDataKalsemen("4332")
                    "Liga Spanyol" -> presenter.getDataKalsemen("4335")
                    "Liga America" -> presenter.getDataKalsemen("4346")
                    "Liga Portugal" -> presenter.getDataKalsemen("4344")
                    "Liga Australia" -> presenter.getDataKalsemen("4356")
                    "Liga Scotlaindia" -> presenter.getDataKalsemen("4395")
                    "Liga English League 1" -> presenter.getDataKalsemen("4396")
                    else -> presenter.getDataKalsemen()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

    }

    override fun hideLoading() {
        kalsemenprogress.hide()
        rvKalsemen.visibility = View.VISIBLE
    }

    override fun showLoading() {
        kalsemenprogress.show()
        rvKalsemen.visibility = View.INVISIBLE
    }

    override fun showDataKalsemen(listmatch: List<TableX>) {
        ListKalsemen.clear()
        ListKalsemen.addAll(listmatch)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvKalsemen.layoutManager = layoutManager
        rvKalsemen.adapter = KalsemenAdapter(listmatch, context)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_kalsemen, container, false)
    }
    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

}
