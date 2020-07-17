package com.example.tennis

import com.example.tennis.EventStore.domainMap
import com.example.tennis.EventStore.transitMap
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.PublishSubject

@Suppress("UNCHECKED_CAST")
sealed class Event<I,O> {
    class PlayerOneScored<I,O>: Event<I,O>()
    class PlayerTwoScored<I,O>: Event<I,O>()
    class PlayerOneToDeuce<I,O>: Event<I,O>()
    class PlayerTwoToDeuce<I,O>: Event<I,O>()
    fun publishEvent(value: I? =  null, id: String = DEFAULT_ID){
        val eventName = this.javaClass.simpleName
        val domainValue = domainMap[eventName]?.run{(this as (I?) -> Any).invoke(value)}
        transitMap[eventName+id]?.also{it.onNext(domainValue)}
    }
    fun registerEvent(handler: (O) -> Unit, id: String = DEFAULT_ID) : Disposable?{
        val eventName = this.javaClass.simpleName
        if (transitMap[eventName+id] == null) {
            transitMap[eventName+id] = PublishSubject.create<Any>()
        }
        val wrapperHandler = {any: Any -> handler.invoke(any as O)}
        return transitMap[eventName+id]?.subscribe(wrapperHandler)
    }
    fun unregisterEvent(id: String = DEFAULT_ID){
        val eventName = this.javaClass.simpleName
        transitMap[eventName+id]?.also{
            if (it.hasObservers()){ transitMap[eventName+id] = null }
        }
    }
    fun setDomain(domainProcess: (Any) -> O){
        val eventName = this.javaClass.simpleName
        domainMap[eventName] = domainProcess
    }
}

