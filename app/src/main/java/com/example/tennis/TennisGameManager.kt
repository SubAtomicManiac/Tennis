package com.example.tennis

import com.example.tennis.Scores.*

enum class Scores {
    ZERO,FIFTEEN,THIRTY,FORTY,DEUCE,ADVANTAGE,WIN,LOSE
}

class TennisGameManager {
    private var playerOneScore = Player(ZERO, 0)
    private var playerTwoScore = Player(ZERO, 1)

    init {
        playerOneScored.setDomain(::updatePlayerOneScore)
        playerTwoScored.setDomain(::updatePlayerTwoScore)
        playerOneToDeuce.setDomain(::easy)
        playerTwoToDeuce.setDomain(::easy)
    }

    private fun updatePlayerOneScore(any: Any?) = updatePlayerScore(playerOneScore,playerTwoScore)
    private fun updatePlayerTwoScore(any: Any?) = updatePlayerScore(playerTwoScore,playerOneScore)
    private fun easy(any: Any?) = DEUCE.name
    private fun updatePlayerScore(player: Player, otherPlayer: Player) : String{
        player.score = when (player.score){
            ZERO -> FIFTEEN
            FIFTEEN -> THIRTY
            THIRTY -> if (otherPlayer.score == FORTY) {
                if (player.order == 0) playerTwoToDeuce.publishEvent(null)
                else playerOneToDeuce.publishEvent(null)
                DEUCE.also { otherPlayer.score = DEUCE }
            } else FORTY
            DEUCE -> if (otherPlayer.score == ADVANTAGE) {
                if (player.order == 0) playerTwoToDeuce.publishEvent(null)
                else playerOneToDeuce.publishEvent(null)
                DEUCE.also { otherPlayer.score = DEUCE }
            } else ADVANTAGE
            ADVANTAGE -> WIN
            FORTY -> WIN
            WIN -> WIN
        }
        return player.score.name
    }

}
