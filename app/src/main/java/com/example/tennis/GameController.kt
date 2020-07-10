package com.example.tennis

class GameController : BaseController(){
    fun addPointForPlayerOne(){
        playerOneScored.publishEvent(null)
    }
    fun addPointForPlayerTwo(){
        playerTwoScored.publishEvent(null)
    }
    fun createGame(){
        val tennisGame = TennisGameManager()
    }
}
