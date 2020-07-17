package com.example.tennis.library

import androidx.lifecycle.ViewModel
import androidx.activity.ComponentActivity
import androidx.annotation.MainThread
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider

open class BaseController : ViewModel()

@MainThread
inline fun <reified C : ViewModel> ComponentActivity.controllers(
    noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null
): Lazy<C> {
    val factoryPromise = factoryProducer ?: {
        defaultViewModelProviderFactory
    }

    return ViewModelLazy(C::class, { viewModelStore }, factoryPromise)
}
