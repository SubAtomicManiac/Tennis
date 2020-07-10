package com.example.tennis

import com.example.tennis.viewmodel.RxViewModel

class TennisViewModel : RxViewModel() {
    var playerOneScore = DEFAULT_SCORE
    var playerTwoScore = DEFAULT_SCORE
    init {
        subscribe<String>(playerOneScored,{ score -> playerOneScore = score})
        subscribe<String>(playerTwoScored,{ score -> playerTwoScore = score})
    }
}
