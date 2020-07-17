package com.example.tennis.presenter

import com.example.tennis.domain.entities.GameScore
import com.example.tennis.domain.entities.Scores
import com.example.tennis.domain.entities.Scores.THIRTY
import com.example.tennis.domain.entities.Scores.ZERO
import com.example.tennis.presenter.TennisPresenter.Companion.THIRTY_DISPLAY
import com.example.tennis.presenter.TennisPresenter.Companion.ZERO_DISPLAY
import io.mockk.every
import io.mockk.mockk
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class TennisPresenterTest {

    private lateinit var presenter: TennisPresenter

    private val gameScoreMock = mockk<GameScore>()

    @Before
    fun setup(){
        presenter = TennisPresenter.create()
    }

    @Test
    fun postGameScoreTest() {
        every {gameScoreMock.playerOne.score} answers { ZERO }
        every {gameScoreMock.playerTwo.score} answers { THIRTY }
        assertEquals(expectedGameScoreDisplay, presenter.postGameScore(gameScoreMock))
    }

    companion object {
        private val expectedGameScoreDisplay = Pair(ZERO_DISPLAY, THIRTY_DISPLAY)
    }
}
