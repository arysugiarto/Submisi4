package com.arysugiarto.submisi.footballmatchschedule.ui.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.arysugiarto.submisi.footballmatchschedule.R
import com.arysugiarto.submisi.footballmatchschedule.ui.fragment.FavoriteMainFragment
import com.arysugiarto.submisi.footballmatchschedule.ui.fragment.LeagueActivity
import com.arysugiarto.submisi.footballmatchschedule.ui.fragment.MainFragment
import com.rahmat.app.footballclub.feature.team.TeamsFragment
import kotlinx.android.synthetic.main.bottom_navigation.*
import kotlinx.android.synthetic.main.home_activity.*


class MainActivity : AppCompatActivity(), MainView.View {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        setSupportActionBar(toolbar_main)

        bottom_navigation.setOnNavigationItemSelectedListener { item : MenuItem->
            when (item.itemId) {
                R.id.lastMatch -> {
                    loadLastMatch(savedInstanceState)
                }
                R.id.team -> {
                    loadTeam(savedInstanceState)
                }
                R.id.listLiga -> {
                    val intent = Intent(this, LeagueActivity::class.java)
                    startActivity(intent)
                }
                R.id.fav -> {
                    loadFavoritesMatch(savedInstanceState)
                }

            }
            true
        }
        bottom_navigation.selectedItemId = R.id.lastMatch
    }

    private fun loadLastMatch(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, MainFragment(), MainFragment::class.java.simpleName)
                .commit()
        }
    }

   private fun loadFavoritesMatch(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, FavoriteMainFragment(), FavoriteMainFragment::class.java.simpleName)
                    .commit()
        }
    }
    private fun loadTeam(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, TeamsFragment(), TeamsFragment::class.java.simpleName)
                .commit()
        }
    }

}
