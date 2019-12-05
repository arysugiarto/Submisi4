package com.arysugiarto.submisi.footballmatchschedule.feature.ui.fragment.nextfragment

import com.arysugiarto.submisi.footballmatchschedule.entity.Match
import com.arysugiarto.submisi.footballmatchschedule.entity.repository.Repository
import com.arysugiarto.submisi.footballmatchschedule.ui.fragment.MatchView
import com.arysugiarto.submisi.footballmatchschedule.ui.fragment.nextfragment.NextPresenter
import com.arysugiarto.submisi.footballmatchschedule.utils.SchedulerProvider
import com.arysugiarto.submisi.footballmatchschedule.utils.TestSchedulerProvider

import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class NextPresenterTest {
    @Mock
    lateinit var mView: MatchView.View

    @Mock
    lateinit var repository: Repository

    lateinit var scheduler: SchedulerProvider

    lateinit var mPresenter: NextPresenter

    lateinit var match : Match

    lateinit var footballMatch: Flowable<Match>

    private val event = mutableListOf<com.arysugiarto.submisi.footballmatchschedule.entity.List>()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        scheduler = TestSchedulerProvider()
        match = Match(event)
        footballMatch = Flowable.just(match)
        mPresenter = NextPresenter(mView, repository, scheduler)
        Mockito.`when`(repository.getNextMatch("4328")).thenReturn(footballMatch)
    }

    @Test
    fun getFootballMatchData() {
        mPresenter.getDataMatch()
        Mockito.verify(mView).showLoading()
        Mockito.verify(mView).showDataMatch(event)
        Mockito.verify(mView).hideLoading()
    }
}