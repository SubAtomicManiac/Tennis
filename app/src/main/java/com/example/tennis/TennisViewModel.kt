package com.example.tennis

import com.example.tennis.viewmodel.RxViewModel

class TennisViewModel : RxViewModel() {
    var playerOneScore = DEFAULT_SCORE
    var playerTwoScore = DEFAULT_SCORE
    init {
        subscribe(playerOneScored,{ score -> playerOneScore = score})
        subscribe(playerTwoScored,{ score -> playerTwoScore = score})
        subscribe(playerOneToDeuce, {score -> playerOneScore = score})
        subscribe(playerTwoToDeuce, {score -> playerTwoScore = score})
    }
}
