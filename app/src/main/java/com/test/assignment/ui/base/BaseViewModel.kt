package com.test.assignment.ui.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    fun handleError(error: Throwable) {
        // Handle the error here
    }
}
