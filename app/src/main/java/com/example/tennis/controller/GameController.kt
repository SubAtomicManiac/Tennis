package com.example.tennis.controller

import com.example.tennis.library.BaseController
import com.example.tennis.domain.TennisGame
import com.example.tennis.event.playerOneScored
import com.example.tennis.event.playerTwoScored
import com.example.tennis.presenter.TennisPresenter

class GameController : BaseController(){
    fun addPointForPlayerOne(){
        playerOneScored.publishEvent()
    }
    fun addPointForPlayerTwo(){
        playerTwoScored.publishEvent()
    }
    fun createGame(){
        TennisGame()
        TennisPresenter()
    }
}
