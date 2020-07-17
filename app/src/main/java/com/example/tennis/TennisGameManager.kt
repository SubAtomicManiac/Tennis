package com.example.tennis

import com.example.tennis.Scores.*

enum class Scores {
    ZERO,FIFTEEN,THIRTY,FORTY,ADVANTAGE,WIN,LOSE
}

class TennisGameManager {
    private val gameScore = GameScore(Player(ZERO),Player(ZERO))

    init {
        playerOneScored.setDomain(::updatePlayerOneScore)
        playerTwoScored.setDomain(::updatePlayerTwoScore)
    }

    private fun updatePlayerOneScore(any: Any?) = updateScore(gameScore.playerOne, gameScore.playerTwo)
    private fun updatePlayerTwoScore(any: Any?) = updateScore(gameScore.playerTwo, gameScore.playerOne)

    private fun updateScore(player: Player, otherPlayer: Player) : GameScore{
        player.score = when (player.score){
            ZERO -> FIFTEEN
            FIFTEEN -> THIRTY
            THIRTY -> FORTY
            FORTY -> when (otherPlayer.score){
                FORTY -> ADVANTAGE.also{otherPlayer.score = FORTY }
                ADVANTAGE -> FORTY.also{otherPlayer.score = FORTY }
                else -> WIN.also{otherPlayer.score = LOSE }
            }
            ADVANTAGE -> WIN.also{otherPlayer.score = LOSE }
            WIN -> WIN.also{otherPlayer.score = LOSE }
            LOSE -> LOSE.also{otherPlayer.score = WIN }
        }
        return gameScore
    }

}
