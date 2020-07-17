package com.example.tennis.domain

import androidx.annotation.VisibleForTesting
import com.example.tennis.domain.entities.GameScore
import com.example.tennis.domain.entities.Player
import com.example.tennis.domain.entities.Scores.*
import com.example.tennis.event.*


class TennisGame private constructor(){
    private val gameScore = GameScore()

    init {
        setDomain()
    }

    //Sets the domain for the event
    private fun setDomain(){
        PlayerOneScoredClick.event.setDomain(::updatePlayerOneScore)
        PlayerTwoScoredClick.event.setDomain(::updatePlayerTwoScore)
        ResetClick.event.setDomain(::resetScore)
    }

    @VisibleForTesting
    fun updatePlayerOneScore(viewOut: Any? = null) = updateScore(gameScore.playerOne, gameScore.playerTwo)
    @VisibleForTesting
    fun updatePlayerTwoScore(viewOut: Any? = null) = updateScore(gameScore.playerTwo, gameScore.playerOne)

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

    private fun resetScore(viewOut: Any? = null) = gameScore.apply{reset()}

    companion object {
        fun create() = TennisGame()
    }

}
