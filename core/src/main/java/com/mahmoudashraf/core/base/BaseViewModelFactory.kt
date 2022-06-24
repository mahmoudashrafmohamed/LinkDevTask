package com.mahmoudashraf.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner


inline fun <reified T : ViewModel> getViewModel(
    owner: ViewModelStoreOwner,
    noinline creator: (() -> T)? = null
): T = if (creator == null) {
    ViewModelProvider(owner).get(T::class.java)
} else {
    ViewModelProvider(owner, BaseViewModelFactory(creator)).get(T::class.java)
}


class BaseViewModelFactory<T>(val creator: () -> T) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return creator() as T
    }
}

typealias Function<T, R> = (T) -> R
