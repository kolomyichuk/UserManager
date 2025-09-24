package com.example.getuserwithretrofitmvi.ui.screens.player

sealed class PlayerIntent {
    data class Play(val url: String) : PlayerIntent()
    data object Pause : PlayerIntent()
    data object Stop : PlayerIntent()
}
