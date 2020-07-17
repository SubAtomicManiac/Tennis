package com.example.tennis.domain.entities

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class GameScoreTest {

    private lateinit var gameScore : GameScore

    @Before
    fun setup(){
        gameScore = GameScore(Player(Scores.FORTY), Player(Scores.THIRTY))
    }

    @Test
    fun resetTest() {
        assertEquals(expectedGameScore, gameScore)
        gameScore.reset()
        assertEquals(expectedResetGameScore,gameScore)
    }

    companion object {
        private val expectedGameScore = GameScore(Player(Scores.FORTY), Player(Scores.THIRTY))
        private val expectedResetGameScore = GameScore()
    }
}
