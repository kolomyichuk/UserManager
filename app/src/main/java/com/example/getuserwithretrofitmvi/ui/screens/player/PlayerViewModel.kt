package com.example.getuserwithretrofitmvi.ui.screens.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlayerViewModel(
    val player: ExoPlayer
) : ViewModel() {

    private val _playerState = MutableStateFlow(PlayerState())
    val playerState: StateFlow<PlayerState> = _playerState.asStateFlow()

    private var hasMediaItem = false
    private var hideControlsJob: Job? = null

    fun onIntent(intent: PlayerIntent) {
        when (intent) {
            is PlayerIntent.Play -> {
                play(intent.url)
            }

            is PlayerIntent.Pause -> {
                player.pause()
                _playerState.update { it.copy(isPlaying = false) }
            }

            is PlayerIntent.Rewind -> {
                val newPosition = (player.currentPosition - 10_000).coerceAtLeast(minimumValue = 0)
                player.seekTo(newPosition)
            }

            is PlayerIntent.Forward -> {
                val newPosition = (player.currentPosition + 10_000).coerceAtMost(player.duration)
                player.seekTo(newPosition)
            }

            is PlayerIntent.ShowControlsTemporarily -> {
                showControlsTemporarily()
            }
        }
    }

    private fun play(url: String) {
        if (!hasMediaItem) {
            player.setMediaItem(MediaItem.fromUri(url))
            player.prepare()
            hasMediaItem = true
        }
        player.playWhenReady = true
        player.play()
        _playerState.update { it.copy(isPlaying = true) }
    }

    private fun showControlsTemporarily() {
        _playerState.update { it.copy(controlsVisible = true) }

        hideControlsJob?.cancel()
        hideControlsJob = viewModelScope.launch {
            delay(3000)
            _playerState.update { it.copy(controlsVisible = false) }
        }
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }
}
