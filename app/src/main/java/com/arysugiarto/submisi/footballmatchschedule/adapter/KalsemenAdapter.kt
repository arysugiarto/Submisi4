package com.arysugiarto.submisi.footballmatchschedule.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arysugiarto.submisi.footballmatchschedule.R
import com.arysugiarto.submisi.footballmatchschedule.entity.TableX

import kotlinx.android.synthetic.main.item_kalsemen.view.*

class KalsemenAdapter (val teamList: List<TableX>,
                       val context:Context?): RecyclerView.Adapter<KalsemenAdapter.KalsemenViewAdapter>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KalsemenViewAdapter {
        return KalsemenViewAdapter(LayoutInflater.from(context).inflate(R.layout.item_kalsemen, parent, false))
    }

    override fun getItemCount() = teamList.size

    override fun onBindViewHolder(holder: KalsemenViewAdapter, position: Int) {
        holder.bind(teamList[position])
    }


    inner class KalsemenViewAdapter(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(tables: TableX){
            itemView.tvNameKal.text = tables.name
            itemView.tvWin.text = tables.win.toString()
            itemView.tvDraw.text = tables.draw.toString()
            itemView.tvLos.text = tables.loss.toString()
            itemView.tvGoalFor.text = tables.goalsfor.toString()
            itemView.tvDiference.text = tables.goalsdifference.toString()
            itemView.tvGoalagain.text = tables.goalsagainst.toString()
            itemView.tvPlay.text = tables.played.toString()
            itemView.tvTotal.text = tables.total.toString()

        }

    }
}
