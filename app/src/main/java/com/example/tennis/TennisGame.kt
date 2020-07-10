package com.example.tennis

import com.example.tennis.Event.PlayerOneScored
import com.example.tennis.Event.PlayerTwoScored
import com.example.tennis.Scores.*

enum class Scores {
    ZERO,FIFTEEN,THIRTY,FORTY,DEUCE,ADVANTAGE,WIN
}

object TennisGameManager {
    private var playerOneScore = Player(ZERO)
    private var playerTwoScore = Player(ZERO)

    init {
        EventManager.setDomain(PlayerOneScored, ::updatePlayerOneScore)
        EventManager.setDomain(PlayerTwoScored, ::updatePlayerTwoScore)
    }

    private fun updatePlayerOneScore(any: Any?) = updatePlayerScore(playerOneScore,playerTwoScore)
    private fun updatePlayerTwoScore(any: Any?) = updatePlayerScore(playerTwoScore,playerOneScore)
    private fun updatePlayerScore(player: Player, otherPlayer: Player) : Scores{
        player.score = when (player.score){
            ZERO -> FIFTEEN
            FIFTEEN -> THIRTY
            THIRTY -> if (otherPlayer.score == FORTY) DEUCE.also { otherPlayer.score = DEUCE } else FORTY
            DEUCE -> if (otherPlayer.score == ADVANTAGE) DEUCE.also { otherPlayer.score = DEUCE } else ADVANTAGE
            ADVANTAGE -> WIN
            FORTY -> WIN
            WIN -> WIN
        }
        return player.score
    }

}
