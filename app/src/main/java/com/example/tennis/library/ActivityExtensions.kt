package com.example.tennis.library

import androidx.activity.ComponentActivity
import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider

//[WIP] Property delegate for controller to imitate functionality of by viewModels [WIP]

@MainThread
inline fun <reified C : ViewModel> ComponentActivity.controllers(
    noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null
): Lazy<C> {
    val factoryPromise = factoryProducer ?: {
        defaultViewModelProviderFactory
    }

    return ViewModelLazy(C::class, { viewModelStore }, factoryPromise)
}
