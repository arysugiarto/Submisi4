package com.arysugiarto.submisi.footballmatchschedule.feature.ui.fragment.search

import com.arysugiarto.submisi.footballmatchschedule.entity.SearchedMatches
import com.arysugiarto.submisi.footballmatchschedule.entity.repository.Repository
import com.arysugiarto.submisi.footballmatchschedule.ui.search.SearchPresenter
import com.arysugiarto.submisi.footballmatchschedule.ui.search.SearchView
import com.arysugiarto.submisi.footballmatchschedule.utils.SchedulerProvider
import com.arysugiarto.submisi.footballmatchschedule.utils.TestSchedulerProvider
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SearchPresenterTest {
    @Mock
    lateinit var mView: SearchView.View

    @Mock
    lateinit var repository: Repository

    lateinit var scheduler: SchedulerProvider

    lateinit var mPresenter: SearchPresenter

    lateinit var match : SearchedMatches

    lateinit var footballMatch: Flowable<SearchedMatches>

    private val event = mutableListOf<com.arysugiarto.submisi.footballmatchschedule.entity.List>()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        scheduler = TestSchedulerProvider()
        match = SearchedMatches(event)
        footballMatch = Flowable.just(match)
        mPresenter = SearchPresenter(mView, repository,scheduler)
        Mockito.`when`(repository.searchMatches("4328")).thenReturn(footballMatch)
    }

    @Test
    fun getSearchMatch() {
        mPresenter.Cari("4328")
        Mockito.verify(mView).showLoading()
        Mockito.verify(mView).showMatch(event)
        Mockito.verify(mView).hideLoading()
    }
}