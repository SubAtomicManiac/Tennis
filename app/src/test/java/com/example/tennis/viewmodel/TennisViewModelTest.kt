package com.example.tennis.viewmodel

import com.example.tennis.presenter.TennisPresenter.Companion.ZERO_DISPLAY
import com.example.tennis.unittests.EventUnitTest
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TennisViewModelTest : EventUnitTest(){

    private lateinit var viewModel: TennisViewModel

    @Before
    fun setup(){
        viewModel = TennisViewModel()
    }

    @Test
    fun updateScoresTest(){
        assertEquals(viewModel.playerOneScore, ZERO_DISPLAY)
        assertEquals(viewModel.playerTwoScore, ZERO_DISPLAY)
        viewModel.updateScores(Pair(EXPECTED_PLAYER_ONE_SCORE,EXPECTED_PLAYER_TWO_SCORE))
        assertEquals(viewModel.playerOneScore, EXPECTED_PLAYER_ONE_SCORE)
        assertEquals(viewModel.playerTwoScore, EXPECTED_PLAYER_TWO_SCORE)
    }

    companion object {
        private const val EXPECTED_PLAYER_ONE_SCORE = "one"
        private const val EXPECTED_PLAYER_TWO_SCORE = "two"
    }

}
