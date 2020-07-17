package com.example.tennis

val playerOneScored = Event.create<Any?,GameScore,Pair<String,String>>()
val playerTwoScored = Event.create<Any?,GameScore,Pair<String,String>>()
