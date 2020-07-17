package com.example.tennis

data class GameScore(var playerOne : Player, var playerTwo : Player){
    fun updateGameScore(scoringPlayer: Player, otherPlayer: Player, newScore: Pair<Scores,Scores>){
        scoringPlayer.score = newScore.first
        otherPlayer.score = newScore.second
    }
}
