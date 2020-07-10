package com.example.tennis

class GameController : BaseController(){
    fun addPointForPlayerOne(){
        playerOneScored.publishEvent(null)
    }
    fun addPointForPlayerTwo(){
        playerTwoScored.publishEvent(null)
    }
}
