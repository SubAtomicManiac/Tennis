package com.example.tennis

import io.reactivex.rxjava3.subjects.PublishSubject

class EventStream<I>(var id: String, var stream: PublishSubject<I>)
