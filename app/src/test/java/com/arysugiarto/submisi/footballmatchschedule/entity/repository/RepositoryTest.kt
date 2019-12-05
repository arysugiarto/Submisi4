package com.arysugiarto.submisi.footballmatchschedule.entity.repository

import com.arysugiarto.submisi.footballmatchschedule.api.RestApi
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


class RepositoryTest {

    @Mock
    lateinit var restApi: RestApi

    lateinit var repository: Repository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = Repository(restApi)
    }

    @Test
    fun getEventById() {
        repository.getEventById("123")
        verify(restApi).getEventById("123")
    }

    @Test
    fun getNextMatch() {
        repository.getNextMatch("123")
        verify(restApi).getNextMatch("123")
    }

    @Test
    fun getFootballMatch() {
        repository.getLastMatch("123")
        verify(restApi).getLastmatch("123")
    }
}