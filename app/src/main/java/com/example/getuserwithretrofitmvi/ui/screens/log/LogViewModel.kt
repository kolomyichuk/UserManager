package com.example.getuserwithretrofitmvi.ui.screens.log

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.getuserwithretrofitmvi.data.repository.LogRepository
import com.example.getuserwithretrofitmvi.data.worker.LogEventBus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LogViewModel(
    private val logRepository: LogRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LogState())
    val uiState: StateFlow<LogState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            LogEventBus.logRotated.collect {
                val logs = withContext(Dispatchers.IO) { logRepository.read() }
                _uiState.value = _uiState.value.copy(logs = logs)
            }
        }
    }

    fun onIntent(intent: LogIntent) {
        when (intent) {
            is LogIntent.CreateLog -> createLogs()
            is LogIntent.LoadLogs -> loadLogs()
        }
    }

    private fun createLogs() {
        viewModelScope.launch(Dispatchers.IO) {
            val fakeMessages = listOf(
                "NullPointerException",
                "IllegalArgumentException",
                "NumberFormatException",
                "IllegalStateException"
            )

            val message = fakeMessages.random()
            logRepository.write(message)
        }
    }

    private fun loadLogs() {
        viewModelScope.launch(Dispatchers.IO) {
            val logs = logRepository.read()
            _uiState.value = _uiState.value.copy(logs = logs)
        }
    }
}
