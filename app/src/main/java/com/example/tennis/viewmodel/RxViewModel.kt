package com.example.tennis.viewmodel

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.example.tennis.DEFAULT_ID
import com.example.tennis.Event
import io.reactivex.rxjava3.disposables.CompositeDisposable

open class RxViewModel : ViewModel(){
    private val listOfEvents = mutableListOf<Pair<Event<Any,Any>,String>>()
    private val compositeDisposable = CompositeDisposable()

    fun <O>subscribe(event: Event<Nothing,String>, handler: (O) -> Unit, id: String = DEFAULT_ID){
        event.registerEvent<Nothing>(handler,id)?.also{compositeDisposable.add(it)}
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun unsubscribe(){
        listOfEvents.forEach { it.first.unregisterEvent(it.javaClass.simpleName+it.second) }
        compositeDisposable.dispose()
    }

}
