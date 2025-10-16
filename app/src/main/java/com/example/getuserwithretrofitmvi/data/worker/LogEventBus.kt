package com.example.getuserwithretrofitmvi.data.worker

import kotlinx.coroutines.flow.MutableSharedFlow

object LogEventBus {
    val logRotated = MutableSharedFlow<Unit>()
}
