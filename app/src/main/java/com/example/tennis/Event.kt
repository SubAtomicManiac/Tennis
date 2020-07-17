package com.example.tennis

import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.PublishSubject

@Suppress("UNCHECKED_CAST")
class Event<VOut,DOut,POut> private constructor(val eventName: String) {
    private val subjects = mutableMapOf<String, PublishSubject<POut>?>()
    private var domain : ((VOut?) -> DOut) = {input -> input as DOut}
    private var presenter : ((DOut?) -> POut) = {input -> input as POut}

    fun publishEvent(value: VOut? =  null, id: String = DEFAULT_ID){
        subjects[eventName+id]?.also{it.onNext(presenter(domain(value)))}
    }
    fun registerEvent(handler: (POut) -> Unit, id: String = DEFAULT_ID) : Disposable?{
        if (subjects[eventName+id] == null) subjects[eventName+id] = PublishSubject.create<POut>()
        return subjects[eventName+id]?.subscribe(handler)
    }
    fun unregisterEvent(id: String = DEFAULT_ID){
        subjects[eventName+id]?.also{ if (it.hasObservers()){ subjects[eventName+id] = null }}
    }
    fun setDomain(domainProcess: (VOut?) -> DOut){
        domain = domainProcess
    }

    fun setPresenter(presenterProcess: (DOut?) -> POut){
        presenter = presenterProcess
    }

    companion object {
        private var eventName = 0
        fun <VOut,DOut,POut>create() : Event<VOut,DOut,POut>{
            return Event(eventName++.toString())
        }
    }

}

