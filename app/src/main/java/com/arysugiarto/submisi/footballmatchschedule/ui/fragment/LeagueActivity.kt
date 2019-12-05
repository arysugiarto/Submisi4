package com.arysugiarto.submisi.footballmatchschedule.ui.fragment

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.arysugiarto.submisi.footballmatchschedule.R
import com.arysugiarto.submisi.footballmatchschedule.adapter.LeagueAdapter
import com.arysugiarto.submisi.footballmatchschedule.entity.League
import com.arysugiarto.submisi.footballmatchschedule.ui.detail.DetailLigaActivity
import kotlinx.android.synthetic.main.activity_league.*

class LeagueActivity : AppCompatActivity() {

    private var items : MutableList<League> = mutableListOf()
    private lateinit var adapter : LeagueAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league)

        initData()
    }

    fun getItemClick(item : League){
        val intent = Intent(this, DetailLigaActivity::class.java)
        intent.putExtra("id_liga",item.idLiga)
        startActivity(intent)
    }
    private fun initData(){
        val name = resources.getStringArray(R.array.league_name)
        val image = resources.obtainTypedArray(R.array.img_league)
        val idLiga = resources.getStringArray(R.array.idLeague)

        items.clear()
        for (i in name.indices) {
            items.add(League(name[i],image.getResourceId(i, 0),idLiga[i]))
        }
        adapter =LeagueAdapter(this,items,{liga : League -> getItemClick(liga)})
        rvLiga.adapter = adapter
        rvLiga.setHasFixedSize(true)
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        rvLiga.layoutManager = layoutManager
        adapter!!.notifyDataSetChanged()
        image.recycle()
    }

}
