package com.example.tennis.viewmodel

import androidx.databinding.Bindable
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
    private val listOfEvents = mutableListOf<Pair<Event<Any,Any>,String>>()
    private val compositeDisposable = CompositeDisposable()

    fun <O>subscribe(event: Event<Any?,O>, handler: (O) -> Unit, id: String = DEFAULT_ID){
        val wrappedHandler = {input : O -> handler(input).also{notifyChange()}}
        event.registerEvent(wrappedHandler,id)?.also{compositeDisposable.add(it)}
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun unsubscribe(){
        listOfEvents.forEach { it.first.unregisterEvent(it.javaClass.simpleName+it.second) }
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

    /**
     * Notifies listeners that all properties of this instance have changed.
     */
    fun notifyChange() {
        synchronized(this) {
            if (mCallbacks == null) {
                return
            }
        }
        mCallbacks!!.notifyCallbacks(this, 0, null)
    }

    /**
     * Notifies listeners that a specific property has changed. The getter for the property
     * that changes should be marked with [Bindable] to generate a field in
     * `BR` to be used as `fieldId`.
     *
     * @param fieldId The generated BR id for the Bindable field.
     */
    fun notifyPropertyChanged(fieldId: Int) {
        synchronized(this) {
            if (mCallbacks == null) {
                return
            }
        }
        mCallbacks!!.notifyCallbacks(this, fieldId, null)
    }

}
