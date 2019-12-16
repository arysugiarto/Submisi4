package com.arysugiarto.submisi.footballmatchschedule.ui.detail.teamdetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import com.arysugiarto.submisi.footballmatchschedule.R
import com.arysugiarto.submisi.footballmatchschedule.adapter.PagerAdapter
import com.arysugiarto.submisi.footballmatchschedule.entity.Team
import com.arysugiarto.submisi.footballmatchschedule.entity.db.FavoriteTeam
import com.arysugiarto.submisi.footballmatchschedule.entity.repository.RepoFavorite
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.rahmat.app.footballclub.feature.teamovw.TeamOvwFragment
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.toast


class TeamDetailActivity : AppCompatActivity(), TeamDetailContract.View {

    private var isFavorite: Boolean = false
    private var menuItem: Menu? = null

    override fun setFavoriteState(favList: List<FavoriteTeam>) {
        if(!favList.isEmpty()) isFavorite = true
    }

    lateinit var team: Team
    lateinit var mPresenter: TeamDetailContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)
        setSupportActionBar(toolbar)

        team = if(savedInstanceState != null){
            savedInstanceState.getParcelable("team")
        }else{
            intent.getParcelableExtra("team")
        }
        val bundle = Bundle()
        bundle.putParcelable("teams", team)
        supportActionBar?.title = team.strTeam
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        loadImage()

        val localRepo = RepoFavorite(applicationContext)
        mPresenter = TeamDetailPresenter(this, localRepo)
//        mPresenter.checkTeam(team.idTeam)

        val adapter = PagerAdapter(supportFragmentManager)
        val teamFragment = TeamOvwFragment()
//        val playersFragment = PlayersFragment()
        teamFragment.arguments = bundle
//        playersFragment.arguments = bundle
        adapter.populateFragment(teamFragment, "Team Overview")
//        adapter.populateFragment(playersFragment, "Players")
        viewpagerTeam.adapter = adapter
        tabs.setupWithViewPager(viewpagerTeam)
    }

    private fun loadImage(){
        if (!team.strTeamFanart1.equals(null)){
        Glide.with(applicationContext)
                .load(team.strTeamFanart1)
                .apply(RequestOptions().placeholder(R.drawable.
                        ic_loading))
                .apply(RequestOptions().override(220, 160))
                .into(imageTeam)
        }else{
            Glide.with(applicationContext)
                    .load(team.strTeamBadge)
                    .apply(RequestOptions().placeholder(R.drawable.ic_loading))
                    .apply(RequestOptions().override(120, 140))
                    .into(imageTeam)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.team_menu, menu)
        menuItem = menu
        setFavorite()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.favorite -> {
                if (!isFavorite){
                    mPresenter.insertTeam(team.idTeam, team.strTeamBadge)
                    toast("Team added to favorite")
                    isFavorite = !isFavorite
                }else{
                    mPresenter.deleteTeam(team.idTeam)
                    toast("Team removed from favorite")
                    isFavorite = !isFavorite
                }
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_fav_24dp)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_border_black_24dp)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelable("team", team)

    }
}
