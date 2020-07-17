package com.example.tennis.viewmodel

import androidx.databinding.Observable
import androidx.databinding.Observable.OnPropertyChangedCallback
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.example.tennis.DEFAULT_ID
import com.example.tennis.Event
import io.reactivex.rxjava3.disposables.CompositeDisposable

open class RxViewModel : ViewModel(), Observable{
    private val listOfEvents = mutableListOf<Pair<Event<*, *, *>,String>>()
    private val compositeDisposable = CompositeDisposable()

    fun <VOut,DOut,POut>subscribe(event: Event<VOut,DOut,POut>, handler: (POut) -> Unit, id: String = DEFAULT_ID){
        val wrappedHandler : (input:POut) -> Unit = {input -> handler(input); notifyChange()}
        event.registerEvent(wrappedHandler,id)?.also{compositeDisposable.add(it)}
        listOfEvents.add(Pair(event, id))
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun unsubscribe(){
        listOfEvents.forEach { it.first.unregisterEvent(it.first.eventName + it.second) }
        compositeDisposable.dispose()
    }

    @Transient
    private var mCallbacks: PropertyChangeRegistry? = null

    override fun addOnPropertyChangedCallback(callback: OnPropertyChangedCallback) {
        synchronized(this) {
            if (mCallbacks == null) {
                mCallbacks = PropertyChangeRegistry()
            }
        }
        mCallbacks!!.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: OnPropertyChangedCallback) {
        synchronized(this) {
            if (mCallbacks == null) {
                return
            }
        }
        mCallbacks!!.remove(callback)
    }

    private fun notifyChange() {
        synchronized(this) {
            if (mCallbacks == null) {
                return
            }
        }
        mCallbacks!!.notifyCallbacks(this, 0, null)
    }
}
