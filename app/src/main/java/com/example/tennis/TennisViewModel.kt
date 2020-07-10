package com.example.tennis

import com.example.tennis.Event.playerOneScored
import com.example.tennis.Event.PlayerTwoScored
import com.example.tennis.viewmodel.RxViewModel

class TennisViewModel : RxViewModel() {
    var playerOneScore = DEFAULT_SCORE
    var playerTwoScore = DEFAULT_SCORE
    init {
        subscribe(playerOneScored,{ score -> playerOneScore = score as String})
        subscribe(PlayerTwoScored,{ score -> playerTwoScore = score as String})
    }
}
