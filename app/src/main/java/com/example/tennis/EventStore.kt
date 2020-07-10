package com.example.tennis

import io.reactivex.rxjava3.subjects.PublishSubject

object EventStore {
    val transitMap = mutableMapOf<String, PublishSubject<Any>?>()
    val domainMap = mutableMapOf<String,Any>()
}
