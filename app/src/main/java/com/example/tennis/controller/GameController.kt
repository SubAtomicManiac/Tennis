package com.example.tennis.controller

import com.example.tennis.library.BaseController
import com.example.tennis.domain.TennisGame
import com.example.tennis.event.playerOneScoredClick
import com.example.tennis.event.playerTwoScoredClick
import com.example.tennis.event.resetClick
import com.example.tennis.presenter.TennisPresenter

class GameController : BaseController(){
    fun addPointForPlayerOne(){
        playerOneScoredClick.publishEvent()
    }
    fun addPointForPlayerTwo(){
        playerTwoScoredClick.publishEvent()
    }
    fun resetGame(){
        resetClick.publishEvent()
    }
    fun createGame(){
        TennisGame.create()
        TennisPresenter.create()
    }
}
