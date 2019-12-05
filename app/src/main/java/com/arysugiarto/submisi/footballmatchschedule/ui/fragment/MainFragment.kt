package com.arysugiarto.submisi.footballmatchschedule.ui.fragment

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.*
import android.support.v7.widget.SearchView
import com.arysugiarto.submisi.footballmatchschedule.R
import com.arysugiarto.submisi.footballmatchschedule.adapter.PagerAdapter
import com.arysugiarto.submisi.footballmatchschedule.ui.fragment.lastfragment.LastFragment
import com.arysugiarto.submisi.footballmatchschedule.ui.fragment.nextfragment.NextFragment
import com.arysugiarto.submisi.footballmatchschedule.ui.search.SearchActivity
import org.jetbrains.anko.startActivity

class  MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_matches, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager = view.findViewById<ViewPager>(R.id.pager)
        val tabs = view.findViewById<TabLayout>(R.id.tablayout)
        val adapter = PagerAdapter(childFragmentManager)
        setHasOptionsMenu(true)
        adapter.populateFragment(LastFragment(), "Last Match")
        adapter.populateFragment(NextFragment(), "Next match")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.search, menu)

        val searchView = menu?.findItem(R.id.actionSearch)?.actionView as SearchView?
        searchView?.queryHint = "Cari Pertandingan"

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                context?.startActivity<SearchActivity>("query" to query)
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })


    }

}
