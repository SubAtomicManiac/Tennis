package com.example.tennis.viewmodel

import androidx.annotation.VisibleForTesting
import com.example.tennis.event.*
import com.example.tennis.library.EventViewModel
import com.example.tennis.presenter.TennisPresenter.Companion.ZERO_DISPLAY

class TennisViewModel : EventViewModel() {
    var playerOneScore = ZERO_DISPLAY
    var playerTwoScore = ZERO_DISPLAY
    init {
        subscribeToEvents()
    }

    private fun subscribeToEvents(){
        subscribe(PlayerOneScoredClick.event, ::updateScores)
        subscribe(PlayerTwoScoredClick.event, ::updateScores)
        subscribe(ResetClick.event, ::updateScores)
    }

    @VisibleForTesting
    fun updateScores(scores: Pair<String,String>){
        playerOneScore = scores.first
        playerTwoScore = scores.second
    }
}
