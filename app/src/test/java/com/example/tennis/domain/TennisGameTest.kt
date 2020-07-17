package com.example.tennis.domain

import com.example.tennis.domain.entities.GameScore
import com.example.tennis.domain.entities.Player
import com.example.tennis.domain.entities.Scores.*
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class TennisGameTest {

    private lateinit var tennisGame : TennisGame

    @Before
    fun setup(){
        tennisGame = TennisGame.create()
    }

    //Usually only public methods are tested
    //However the Domain, Presenter and ViewModel don't need public methods as they don't have callable methods
    //To still observe testability we make the exception by testing every method that publishes it's result to the event bus

    @Test
    fun updatePlayerOneScore() {
        assertEquals(score_0_15, tennisGame.updatePlayerTwoScore())
        assertEquals(score_0_30, tennisGame.updatePlayerTwoScore())
        assertEquals(score_0_40, tennisGame.updatePlayerTwoScore())
        assertEquals(score_15_40, tennisGame.updatePlayerOneScore())
        assertEquals(score_30_40, tennisGame.updatePlayerOneScore())
        assertEquals(score_40_40, tennisGame.updatePlayerOneScore())
        assertEquals(score_40_adv, tennisGame.updatePlayerTwoScore())
        assertEquals(score_40_40, tennisGame.updatePlayerOneScore())
        assertEquals(score_adv_40, tennisGame.updatePlayerOneScore())
        assertEquals(score_win_lose, tennisGame.updatePlayerOneScore())
    }

    @Test
    fun updatePlayerTwoScore() {
        assertEquals(score_0_15, tennisGame.updatePlayerTwoScore())
        assertEquals(score_0_30, tennisGame.updatePlayerTwoScore())
        assertEquals(score_0_40, tennisGame.updatePlayerTwoScore())
        assertEquals(score_lose_win, tennisGame.updatePlayerTwoScore())
    }

    companion object {
        private val score_0_15 = GameScore(Player(ZERO),Player(FIFTEEN))
        private val score_0_30 = GameScore(Player(ZERO),Player(THIRTY))
        private val score_0_40 = GameScore(Player(ZERO),Player(FORTY))
        private val score_lose_win = GameScore(Player(LOSE), Player(WIN))
        private val score_15_40 = GameScore(Player(FIFTEEN), Player(FORTY))
        private val score_30_40 = GameScore(Player(THIRTY), Player(FORTY))
        private val score_40_40 = GameScore(Player(FORTY), Player(FORTY))
        private val score_40_adv = GameScore(Player(FORTY),Player(ADVANTAGE))
        private val score_adv_40 = GameScore(Player(ADVANTAGE), Player(FORTY))
        private val score_win_lose = GameScore(Player(WIN), Player(LOSE))
    }
}
