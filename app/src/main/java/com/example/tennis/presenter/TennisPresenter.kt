package com.example.tennis.presenter

import com.example.tennis.domain.entities.GameScore
import com.example.tennis.domain.entities.Scores
import com.example.tennis.domain.entities.Scores.*
import com.example.tennis.event.playerOneScored
import com.example.tennis.event.playerTwoScored

class TennisPresenter {

    init {
        playerOneScored.setPresenter(::postGameScore)
        playerTwoScored.setPresenter(::postGameScore)
    }

    private fun postGameScore(gameScore: GameScore?) = Pair(
        scoresToDisplay(gameScore?.run{this.playerOne.score}),
        scoresToDisplay(gameScore?.run{this.playerTwo.score})
    )

    private fun scoresToDisplay(scores : Scores?) = when(scores) {
        ZERO -> ZERO_DISPLAY
        FIFTEEN -> FIFTEEN_DISPLAY
        THIRTY -> THIRTY_DISPLAY
        FORTY -> FORTY_DISPLAY
        ADVANTAGE -> ADVANTAGE_DISPLAY
        WIN -> WIN_DISPLAY
        LOSE -> LOSE_DISPLAY
        else -> ERROR
    }

    companion object {
        const val ZERO_DISPLAY = "0"
        const val FIFTEEN_DISPLAY = "15"
        const val THIRTY_DISPLAY = "30"
        const val FORTY_DISPLAY = "40"
        const val ADVANTAGE_DISPLAY = "Adv"
        const val WIN_DISPLAY = "Win"
        const val LOSE_DISPLAY = "Lose"
        const val ERROR = "Error"
    }
}
