package com.example.tennis.controller

import com.example.tennis.library.Event
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class GameControllerTest {

    lateinit var controller: GameController

    @MockK
    var playerOneScoredClickMock: Event<Any,Any,Any> = mockk()

    @Before
    fun setup(){
        controller = GameController()
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun addPointForPlayerOne() {
        every {playerOneScoredClickMock.publishEvent()} just Runs
        controller.addPointForPlayerOne()
        verify{playerOneScoredClickMock.publishEvent()}
    }

    @Test
    fun addPointForPlayerTwo() {
    }

    @Test
    fun resetGame() {
    }

    @Test
    fun createGame() {
    }
}
