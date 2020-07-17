package com.example.tennis

import com.example.tennis.viewmodel.RxViewModel

class TennisViewModel : RxViewModel() {
    var playerOneScore = ZERO_DISPLAY
    var playerTwoScore = ZERO_DISPLAY
    init {
        subscribe(playerOneScored, ::updateScores)
        subscribe(playerTwoScored, ::updateScores)
    }

    fun updateScores(scores: Pair<String,String>){
        playerOneScore = scores.first
        playerTwoScore = scores.second
    }
}
