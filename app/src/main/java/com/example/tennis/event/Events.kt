package com.example.tennis.event

import com.example.tennis.library.Event
import com.example.tennis.domain.entities.GameScore

val playerOneScoredClick = Event.create<Any?, GameScore,Pair<String,String>>()
val playerTwoScoredClick = Event.create<Any?, GameScore,Pair<String,String>>()
val resetClick = Event.create<Any?, GameScore, Pair<String,String>>()
