package com.example.tennis

import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.PublishSubject

object EventManager {
    private val transitMap = mutableMapOf<Event, MutableMap<String, PublishSubject<Any>?>>()
    private val domainMap = mutableMapOf<Event,(Any?) -> Any>()
    fun setDomain(event: Event, handler: (Any?) -> Any){
        domainMap[event] = handler
    }
    fun <T>publishEvent(event: Event, value: T? =  null, id: String = DEFAULT_ID){
        transitMap[event]?.also{it[id]?.onNext(domainMap[event]?.invoke(value))}
    }
    fun registerEvent(event: Event, handler: (Any) -> Unit, id: String = DEFAULT_ID) : Disposable?{
        if (transitMap[event] == null) {
            transitMap[event] = mutableMapOf(Pair<String,PublishSubject<Any>?>(id,PublishSubject.create()))
        }
        return transitMap[event]?.run{this[id]?.subscribe(handler)}
    }
    fun unregisterEvent(event: Event, id: String = DEFAULT_ID){
        transitMap[event]?.also{
           it[id]?.also{ eventStream -> if (eventStream.hasObservers()){ it[id] = null }}
        }
    }
}
