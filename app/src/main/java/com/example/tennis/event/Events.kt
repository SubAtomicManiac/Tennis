package com.example.tennis.event

import com.example.tennis.library.Event
import com.example.tennis.domain.entities.GameScore

val playerOneScored = Event.create<Any?, GameScore,Pair<String,String>>()
val playerTwoScored = Event.create<Any?, GameScore,Pair<String,String>>()
