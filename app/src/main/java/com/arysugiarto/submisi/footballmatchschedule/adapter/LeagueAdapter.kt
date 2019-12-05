package com.arysugiarto.submisi.footballmatchschedule.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.arysugiarto.submisi.footballmatchschedule.R
import com.arysugiarto.submisi.footballmatchschedule.entity.League
import com.squareup.picasso.Picasso

class LeagueAdapter (private val context: Context , private val items : List<League> , private val listener:(League)->Unit)
    : RecyclerView.Adapter<LeagueAdapter.ViewHolder>()
{


    override fun onCreateViewHolder(viewgroup: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_league, viewgroup, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position],listener)

    }

    class ViewHolder(itemsView : View) : RecyclerView.ViewHolder(itemsView) {
        val ligaBadge: ImageView = itemView.findViewById(R.id.imageLiga)
        val ligaName: TextView = itemView.findViewById(R.id.nameLiga)
        fun bindItem(items : League , listener: (League) -> Unit){
            items.image?.let { Picasso.get().load(it).into(ligaBadge) }
            ligaName.text = items.name
            itemView.setOnClickListener { listener(items) }
        }
    }
}