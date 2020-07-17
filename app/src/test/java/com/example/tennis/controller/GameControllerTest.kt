package com.example.tennis.controller

import com.example.tennis.event.PlayerOneScoredClick
import com.example.tennis.event.PlayerTwoScoredClick
import com.example.tennis.event.ResetClick
import com.example.tennis.presenter.TennisPresenter
import com.example.tennis.unittests.EventUnitTest
import io.mockk.*
import org.junit.Test

import org.junit.Before

class GameControllerTest : EventUnitTest(){

    private lateinit var controller: TennisController

    @Before
    fun setup(){
        controller = TennisController()
    }

    @Test
    fun addPointForPlayerOne() {
        mockPublishEvent(PlayerOneScoredClick.event)
        controller.addPointForPlayerOne()
        verify{PlayerOneScoredClick.event.publishEvent()}
    }

    @Test
    fun addPointForPlayerTwo() {
        mockPublishEvent(PlayerTwoScoredClick.event)
        controller.addPointForPlayerTwo()
        verify{PlayerTwoScoredClick.event.publishEvent()}
    }

    @Test
    fun resetGame() {
        mockPublishEvent(ResetClick.event)
        controller.resetGame()
        verify{ResetClick.event.publishEvent()}
    }

    @Test
    fun createGame() {
        mockkObject(TennisPresenter)
        every {TennisPresenter.create()} returns mockk()
        controller.createGame()
        verify{TennisPresenter.create()}
    }
}
