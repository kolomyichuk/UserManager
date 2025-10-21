package com.example.getuserwithretrofitmvi.ui.screens.log

sealed class LogIntent {
    data object CreateLog : LogIntent()
    data object LoadLogs : LogIntent()
}
