package com.arysugiarto.submisi.footballmatchschedule.ui.fragment

import com.arysugiarto.submisi.footballmatchschedule.entity.Match
import com.arysugiarto.submisi.footballmatchschedule.entity.repository.Repository
import com.arysugiarto.submisi.footballmatchschedule.ui.fragment.lastfragment.LastPresenter
import com.arysugiarto.submisi.footballmatchschedule.utils.SchedulerProvider
import com.arysugiarto.submisi.footballmatchschedule.utils.TestSchedulerProvider
import io.reactivex.Flowable

import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations



class LastPresenterTest {

    @Mock
    lateinit var mView: MatchView.View

    @Mock
    lateinit var repository: Repository

    lateinit var scheduler: SchedulerProvider

    lateinit var mPresenter: LastPresenter

    lateinit var match : Match

    lateinit var footballMatch: Flowable<Match>

    private val event = mutableListOf<com.arysugiarto.submisi.footballmatchschedule.entity.List>()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        scheduler = TestSchedulerProvider()
        match = Match(event)
        footballMatch = Flowable.just(match)
        mPresenter = LastPresenter(mView, repository,scheduler)
        `when`(repository.getLastMatch("4328")).thenReturn(footballMatch)
    }

    @Test
    fun getDataMatch() {
        mPresenter.getDataMatch()
        verify(mView).showLoading()
        verify(mView).showDataMatch(event)
        verify(mView).hideLoading()
    }
}