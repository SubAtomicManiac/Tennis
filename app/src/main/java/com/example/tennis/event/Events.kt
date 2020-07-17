package com.example.tennis.event

import com.example.tennis.library.Event
import com.example.tennis.domain.entities.GameScore

//Key part of the event driven architecture
//Summarizes the functionality of the application while remaining type safe
//Each event is created with the type variables:
//  -First for the type the controller publishes
//  -Secondly for the type the domain/model publishes
//  -Thirdly for the type the presenter publishes to the viewModel
//By default the controllers value is passed from the controller, to domain, to presenter to viewModel
//So it's not necessary to add a domain or presenter to pass a value straight to the viewModel

object PlayerOneScoredClick{val event = Event.create<Any?, GameScore, Pair<String,String>>()}
object PlayerTwoScoredClick{val event = Event.create<Any?, GameScore, Pair<String,String>>()}
object ResetClick{val event = Event.create<Any?, GameScore, Pair<String, String>>()}
