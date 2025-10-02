package com.example.getuserwithretrofitmvi.utils

object FormatterUtils {
    private const val MILLIS_IN_SECOND = 1000
    private const val SECONDS_IN_MINUTE = 60

    fun formatTime(millis: Long): String {
        val totalSeconds = millis / MILLIS_IN_SECOND
        val minutes = totalSeconds / SECONDS_IN_MINUTE
        val seconds = totalSeconds % SECONDS_IN_MINUTE
        return "%02d:%02d".format(minutes, seconds)
    }
}
