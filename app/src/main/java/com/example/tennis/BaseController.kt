package com.example.tennis

import androidx.lifecycle.ViewModel

open class BaseController : ViewModel() {
    private val eventManager = EventManager
    fun publishEvent(event: Event){
        eventManager.publishEvent(event)
    }
}
