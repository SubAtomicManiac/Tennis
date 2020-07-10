package com.example.tennis.viewmodel

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.example.tennis.DEFAULT_ID
import com.example.tennis.Event
import com.example.tennis.EventManager
import io.reactivex.rxjava3.disposables.CompositeDisposable

open class RxViewModel : ViewModel(){
    private val eventManager = EventManager
    private val listOfEvents = mutableListOf<Pair<Event,String>>()
    private val compositeDisposable = CompositeDisposable()

    fun subscribe(event: Event, handler: (Any) -> Unit, id: String = DEFAULT_ID){
        compositeDisposable.add(eventManager.registerEvent(event,handler,id))
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun unsubscribe(event: Event, id: String = DEFAULT_ID){
        listOfEvents.forEach { eventManager.unregisterEvent(it.first, it.second) }
        compositeDisposable.dispose()
    }

}
