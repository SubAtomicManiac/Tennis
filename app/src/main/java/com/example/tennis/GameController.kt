package com.example.tennis

import com.example.tennis.Event.PlayerOneScored
import com.example.tennis.Event.PlayerTwoScored

class GameController : BaseController(){
    fun addPointForPlayerOne(){
        publishEvent(PlayerOneScored())
    }
    fun addPointForPlayerTwo(){
        publishEvent(PlayerTwoScored())
    }
}
