package com.example.tennis

sealed class Event<I,O> {
    class PlayerOneScored<Nothing,String> : Event<Nothing,String>()
    class PlayerTwoScored<Nothing,String> : Event<Nothing,String>()
}
