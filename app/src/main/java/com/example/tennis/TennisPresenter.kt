package com.example.tennis

import com.example.tennis.Scores.*

class TennisPresenter {

    init {
        playerOneScored.setPresenter(::postGameScore)
        playerTwoScored.setPresenter(::postGameScore)
    }

    fun postGameScore(gameScore: GameScore?) = Pair(
        scoresToDisplay(gameScore?.run{this.playerOne.score}),
        scoresToDisplay(gameScore?.run{this.playerTwo.score})
    )

    private fun scoresToDisplay(scores : Scores?) = when(scores) {
        ZERO -> ZERO_DISPLAY
        FIFTEEN -> FIFTHTEEN_DISPLAY
        THIRTY -> THIRTY_DISPLAY
        FORTY -> FORTY_DISPLAY
        ADVANTAGE -> ADVANTAGE_DISPLAY
        WIN -> WIN_DISPLAY
        LOSE -> LOSE_DISPLAY
        else -> ERROR
    }
}
