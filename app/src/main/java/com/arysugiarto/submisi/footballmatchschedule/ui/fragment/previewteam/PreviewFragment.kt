package com.arysugiarto.submisi.footballmatchschedule.ui.fragment.previewteam


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arysugiarto.submisi.footballmatchschedule.R
import com.arysugiarto.submisi.footballmatchschedule.entity.Team
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import kotlinx.android.synthetic.main.fragment_preview.*

class PreviewFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_preview, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val team: Team? = arguments?.getParcelable("teams")
        initView(team)
    }

    fun initView(teamInfo: Team?){
        Glide.with(this)
                .load(teamInfo?.strTeamBadge)
                .apply(RequestOptions().placeholder(R.drawable.ic_loading))
                .into(imgBadge)

        nameTeam.text = teamInfo?.strTeam
        manager.text = teamInfo?.strManager
        stadium.text = teamInfo?.strStadium
        teamPreview.text = teamInfo?.strDescriptionEN
    }


}
