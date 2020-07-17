package com.example.tennis.domain

import com.example.tennis.domain.entities.GameScore
import com.example.tennis.domain.entities.Player
import com.example.tennis.domain.entities.Scores.*
import com.example.tennis.event.playerOneScoredClick
import com.example.tennis.event.playerTwoScoredClick
import com.example.tennis.event.resetClick


class TennisGame private constructor(){
    private val gameScore = GameScore(Player(ZERO), Player(ZERO))

    init {
        playerOneScoredClick.setDomain(::updatePlayerOneScore)
        playerTwoScoredClick.setDomain(::updatePlayerTwoScore)
        resetClick.setDomain(::resetScore)
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

    private fun resetScore(viewOut: Any?) = gameScore.apply{reset()}

    companion object {
        fun create() = TennisGame()
    }

}
