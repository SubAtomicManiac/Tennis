package com.example.tennis.domain

import com.example.tennis.domain.entities.GameScore
import com.example.tennis.domain.entities.Player
import com.example.tennis.domain.entities.Scores.*
import com.example.tennis.event.playerOneScored
import com.example.tennis.event.playerTwoScored



class TennisGame {
    private val gameScore = GameScore(Player(ZERO), Player(ZERO))

    init {
        playerOneScored.setDomain(::updatePlayerOneScore)
        playerTwoScored.setDomain(::updatePlayerTwoScore)
    }

    private fun updatePlayerOneScore(viewOut: Any?) = updateScore(gameScore.playerOne, gameScore.playerTwo)
    private fun updatePlayerTwoScore(viewOut: Any?) = updateScore(gameScore.playerTwo, gameScore.playerOne)

    private fun updateScore(player: Player, otherPlayer: Player) : GameScore {
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
