package com.arysugiarto.submisi.footballmatchschedule.ui.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import com.arysugiarto.submisi.footballmatchschedule.R
import com.arysugiarto.submisi.footballmatchschedule.api.ApiService
import com.arysugiarto.submisi.footballmatchschedule.api.RestApi
import com.arysugiarto.submisi.footballmatchschedule.entity.Team
import com.arysugiarto.submisi.footballmatchschedule.entity.repository.Repository
import com.bumptech.glide.Glide
import com.arysugiarto.submisi.footballmatchschedule.entity.List
import com.arysugiarto.submisi.footballmatchschedule.entity.db.DbFavoriteMatch
import com.arysugiarto.submisi.footballmatchschedule.entity.repository.RepoFavorite
import com.arysugiarto.submisi.footballmatchschedule.utils.AppSchedulerProvider
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.toast

class DetailMatchActivity : AppCompatActivity(), DetailContract.View {


    private var addFavorite: Boolean = false
    private var menuItem: Menu? = null
    lateinit var event: List




    lateinit var presenter : DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val service = ApiService.getClient().create(RestApi::class.java)
        val request = Repository(service)
        val repoFavorite = RepoFavorite(applicationContext)
        val scheduler = AppSchedulerProvider()
        presenter = DetailPresenter(this, request,repoFavorite,scheduler)

        event = intent.getParcelableExtra("match")
        presenter.getTeamKandang(event.idHomeTeam)
        presenter.getTeamTandang(event.idAwayTeam)
        presenter.checkMatch(event.idEvent)
        getData(event)
        supportActionBar?.title = event.strEvent
    }

    override fun logoTeamHome(team: Team) {
        Glide.with(applicationContext)
            .load(team.strTeamBadge)
            .apply(RequestOptions().placeholder(R.drawable.ic_loading))
            .into(kandang)
    }
    override fun logoTeamAway(team: Team) {
        Glide.with(applicationContext)
            .load(team.strTeamBadge)
            .apply(RequestOptions().placeholder(R.drawable.ic_loading))
            .into(tandang)
    }

    fun getData(event:List){
        tanggalMatch.text = event.dateEvent
        namaTeamKandang.text = event.strHomeTeam
        kandangScore.text = event.intHomeScore
        namaTeamTandang.text = event.strAwayTeam
        TandangScore.text = event.intAwayScore
        goalKandang.text = event.strHomeGoalDetails
        goalTandang.text= event.strAwayGoalDetails
        gkKandang.text = event.strHomeLineupGoalkeeper
        gkTandang.text = event.strAwayLineupGoalkeeper
        bekKandang.text = event.strHomeLineupDefense
        bekTandang.text = event.strAwayLineupDefense
        GelandangKandang.text = event.strHomeLineupMidfield
        GelandangTandang.text = event.strAwayLineupMidfield
        penyerangKandang.text = event.strHomeLineupForward
        peyerangTandang.text = event.strAwayLineupForward
        cadanganKandang.text = event.strHomeLineupSubstitutes
        cadanganTandang.text = event.strAwayLineupSubstitutes

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        menuItem = menu
        setFavorite()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.favorites -> {
                if (!addFavorite) {
                    presenter.insertMatch(
                        event.idEvent, event.idHomeTeam, event.idAwayTeam
                    )
                    toast("Di Tambahkan ke Favorite")
                    addFavorite = !addFavorite
                } else {
                    presenter.deleteMatch(event.idEvent)
                    toast("Di Hapus dari Favorite")
                    addFavorite = !addFavorite
                }
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setFavorite() {
        if (addFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_fav_24dp)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_border_black_24dp)
    }

    override fun setFavoriteState(favList: kotlin.collections.List<DbFavoriteMatch>) {
        if(!favList.isEmpty()) addFavorite = true
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroyPresenter()
    }
}
