package com.example.tennis.domain.entities

import com.example.tennis.domain.entities.Scores.ZERO

data class GameScore(var playerOne : Player, var playerTwo : Player){
    fun reset(){
        playerOne.score = ZERO
        playerTwo.score = ZERO
    }
}
