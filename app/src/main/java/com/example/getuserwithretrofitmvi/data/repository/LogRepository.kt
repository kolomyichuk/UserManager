package com.example.getuserwithretrofitmvi.data.repository

import com.example.getuserwithretrofitmvi.data.local.LogFileDataSource

class LogRepository(
    private val logFileDataSource: LogFileDataSource
) {

    suspend fun write(message: String) = logFileDataSource.writeLog(message)

    suspend fun read(): List<String> = logFileDataSource.readLogs()

    suspend fun rotate() = logFileDataSource.rotateLogs()

}
