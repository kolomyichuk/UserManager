package com.example.getuserwithretrofitmvi.ui.screens.player

import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PlayerViewModel(
    val player: ExoPlayer
) : ViewModel() {

    private val _playerState = MutableStateFlow(PlayerState())
    val playerState: StateFlow<PlayerState> = _playerState.asStateFlow()

    fun onIntent(intent: PlayerIntent) {
        when (intent) {
            is PlayerIntent.Play -> {
                player.setMediaItem(MediaItem.fromUri(intent.url))
                player.prepare()
                player.playWhenReady = true
                _playerState.update { it.copy(isPlaying = true) }
            }

            is PlayerIntent.Pause -> {
                player.pause()
                _playerState.update { it.copy(isPlaying = false) }
            }

            is PlayerIntent.Stop -> {
                player.stop()
                _playerState.update { it.copy(isPlaying = false, currentPosition = 0L) }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }
}
