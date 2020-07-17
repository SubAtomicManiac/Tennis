package com.example.tennis.controller

import com.example.tennis.library.EventController
import com.example.tennis.domain.TennisGame
import com.example.tennis.event.*
import com.example.tennis.presenter.TennisPresenter

class TennisController : EventController(){
    fun addPointForPlayerOne(){
        PlayerOneScoredClick.event.publishEvent()
    }
    fun addPointForPlayerTwo(){
        PlayerTwoScoredClick.event.publishEvent()
    }
    fun resetGame(){
        ResetClick.event.publishEvent()
    }
    fun createGame(){
        TennisGame.create()
        TennisPresenter.create()
    }
}
