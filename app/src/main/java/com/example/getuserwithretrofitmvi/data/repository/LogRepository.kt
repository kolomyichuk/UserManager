package com.example.getuserwithretrofitmvi.data.repository

import com.example.getuserwithretrofitmvi.data.local.LogFileDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LogRepository(
    private val logFileDataSource: LogFileDataSource
) {

    init {
        CoroutineScope(Dispatchers.IO).launch {
            _logs.value = logFileDataSource.readLogs()
        }
    }

    private val _logs = MutableStateFlow<List<String>>(emptyList())
    val logs: StateFlow<List<String>> = _logs.asStateFlow()

    suspend fun write(message: String) {
        logFileDataSource.writeLog(message)
        _logs.value = logFileDataSource.readLogs()
    }

    suspend fun read(): List<String> = logFileDataSource.readLogs()

    suspend fun rotate() {
        logFileDataSource.rotateLogs()
        _logs.value = logFileDataSource.readLogs()
    }

}
