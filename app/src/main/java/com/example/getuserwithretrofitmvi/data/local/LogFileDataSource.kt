package com.example.getuserwithretrofitmvi.data.local

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class LogFileDataSource(
    context: Context
) {

    private val logFile = File(context.filesDir, "temp_logs.txt")
    private val archiveFile = File(context.filesDir, "archive_logs.txt")

    suspend fun writeLog(message: String) = withContext(Dispatchers.IO) {
        val formatter = SimpleDateFormat("HH:mm:ss", Locale.US)
        val time = formatter.format(Date())
        logFile.appendText("[$time] $message\n")
    }

    suspend fun readLogs(): List<String> = withContext(Dispatchers.IO) {
        if (!archiveFile.exists()) return@withContext emptyList()
        archiveFile.readLines()
            .filter { it.isNotBlank() }
            .reversed()
    }

    suspend fun rotateLogs() = withContext(Dispatchers.IO) {
        if (!logFile.exists()) return@withContext
        val tempLogs = logFile.readText()
        if (tempLogs.isNotBlank()) {
            archiveFile.appendText(tempLogs)
            logFile.writeText("")
        }
    }

}
