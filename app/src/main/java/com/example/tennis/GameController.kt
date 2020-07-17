package com.example.tennis

class GameController : BaseController(){
    fun addPointForPlayerOne(){
        playerOneScored.publishEvent()
    }
    fun addPointForPlayerTwo(){
        playerTwoScored.publishEvent()
    }
    fun createGame(){
        val tennisGame = TennisGameManager()
    }
}
