package com.arysugiarto.submisi.footballmatchschedule.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter


class PagerAdapter(fragmentManager: FragmentManager?) : FragmentPagerAdapter(fragmentManager){

    var ListFragment = arrayListOf<Fragment>()
    var ListJudul = arrayListOf<String>()

    fun populateFragment(fragment: Fragment,title: String){
        ListFragment.add(fragment)
        ListJudul.add(title)
    }
    override fun getItem(position: Int) = ListFragment[position]

    override fun getCount() = ListFragment.size

    override fun getPageTitle(position: Int) = ListJudul[position]
}