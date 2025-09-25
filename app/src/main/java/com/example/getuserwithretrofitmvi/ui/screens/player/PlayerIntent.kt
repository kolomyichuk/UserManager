package com.example.getuserwithretrofitmvi.ui.screens.player

sealed class PlayerIntent {
    data class Play(val url: String) : PlayerIntent()
    data object Pause : PlayerIntent()
    data object Rewind : PlayerIntent()
    data object Forward : PlayerIntent()
    data object ShowControlsTemporarily : PlayerIntent()
}
