package com.example.tennis.viewmodel

import com.example.tennis.library.RxViewModel
import com.example.tennis.event.playerOneScoredClick
import com.example.tennis.event.playerTwoScoredClick
import com.example.tennis.event.resetClick
import com.example.tennis.presenter.TennisPresenter.Companion.ZERO_DISPLAY

class TennisViewModel : RxViewModel() {
    var playerOneScore = ZERO_DISPLAY
    var playerTwoScore = ZERO_DISPLAY
    init {
        subscribe(playerOneScoredClick, ::updateScores)
        subscribe(playerTwoScoredClick, ::updateScores)
        subscribe(resetClick, ::updateScores)
    }

    private fun updateScores(scores: Pair<String,String>){
        playerOneScore = scores.first
        playerTwoScore = scores.second
    }
}
