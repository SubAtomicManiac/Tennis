package com.example.tennis.unittests

import com.example.tennis.library.Event
import io.mockk.*
import org.junit.After

open class EventUnitTest {
    @After
    fun tearDown() {
        unmockkAll()
    }

    fun mockPublishEvent(event: Event<*, *, *>){
        mockkObject(event)
        every {event.publishEvent()} just Runs
    }
}
