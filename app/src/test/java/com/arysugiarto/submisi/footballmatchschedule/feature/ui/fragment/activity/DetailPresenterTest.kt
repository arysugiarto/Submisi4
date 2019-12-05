package com.arysugiarto.submisi.footballmatchschedule.feature.ui.fragment.activity

import com.arysugiarto.submisi.footballmatchschedule.entity.TeamResponseDetail
import com.arysugiarto.submisi.footballmatchschedule.entity.repository.RepoFavorite
import com.arysugiarto.submisi.footballmatchschedule.entity.repository.Repository
import com.arysugiarto.submisi.footballmatchschedule.ui.detail.DetailContract
import com.arysugiarto.submisi.footballmatchschedule.ui.detail.DetailPresenter
import com.arysugiarto.submisi.footballmatchschedule.utils.SchedulerProvider
import com.arysugiarto.submisi.footballmatchschedule.utils.TestSchedulerProvider
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class DetailPresenterTest {
    @Mock
    lateinit var mView: DetailContract.View

    @Mock
    lateinit var repository: Repository
    @Mock
    lateinit var repoFavorite: RepoFavorite

    lateinit var scheduler: SchedulerProvider

    lateinit var mPresenter: DetailPresenter

    lateinit var match : TeamResponseDetail

    lateinit var footballMatch: Flowable<TeamResponseDetail>

    private val team = mutableListOf<com.arysugiarto.submisi.footballmatchschedule.entity.Team>()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        scheduler = TestSchedulerProvider()
        match = TeamResponseDetail(team)
        footballMatch = Flowable.just(match)
        mPresenter = DetailPresenter(mView, repository,repoFavorite,scheduler)
        Mockito.`when`(repository.getTeams("4328")).thenReturn(footballMatch)
    }

    @Test
    fun getDetailkandang() {
        mPresenter.getTeamKandang("4328")

    }

    @Test
    fun getDetailTandang() {
        mPresenter.getTeamKandang("4328")

    }

}