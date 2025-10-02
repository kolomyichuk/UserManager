package com.example.getuserwithretrofitmvi.ui.screens.player

data class PlayerState(
    val isPlaying: Boolean = false,
    val currentPosition: Long = 0L,
    val controlsVisible: Boolean = true
)
