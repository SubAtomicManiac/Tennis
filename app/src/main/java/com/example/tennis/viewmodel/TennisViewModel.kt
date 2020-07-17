package com.example.tennis.viewmodel

import com.example.tennis.library.RxViewModel
import com.example.tennis.event.playerOneScored
import com.example.tennis.event.playerTwoScored
import com.example.tennis.presenter.TennisPresenter.Companion.ZERO_DISPLAY

class TennisViewModel : RxViewModel() {
    var playerOneScore = ZERO_DISPLAY
    var playerTwoScore = ZERO_DISPLAY
    init {
        subscribe(playerOneScored, ::updateScores)
        subscribe(playerTwoScored, ::updateScores)
    }

    private fun updateScores(scores: Pair<String,String>){
        playerOneScore = scores.first
        playerTwoScore = scores.second
    }
}
