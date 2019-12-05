package com.arysugiarto.submisi.footballmatchschedule.ui.fragment

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.arysugiarto.submisi.footballmatchschedule.R
import com.arysugiarto.submisi.footballmatchschedule.extensions.hide
import com.arysugiarto.submisi.footballmatchschedule.extensions.show
import com.arysugiarto.submisi.footballmatchschedule.ui.fragment.favoritepertandingan.MatchFavoriteFragment
import kotlinx.android.synthetic.main.fragment_match_favorite.*


class FavoriteMainFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                               savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pager = view.findViewById<ViewPager>(R.id.pager)
        val tablayout = view.findViewById<TabLayout>(R.id.tabs)
        val adapter = com.arysugiarto.submisi.footballmatchschedule.adapter.PagerAdapter(childFragmentManager)
        adapter.populateFragment(MatchFavoriteFragment(), "Pertandingan Favorite")
        pager.adapter = adapter
        tablayout.setupWithViewPager(pager)
    }

}
