package com.example.getuserwithretrofitmvi.ui.screens.player

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.media3.ui.compose.PlayerSurface
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

private const val VIDEO_URL = "https://download.blender.org/durian/trailer/sintel_trailer-720p.mp4"

@Composable
fun PlayerScreen(
    viewModel: PlayerViewModel = koinViewModel()
) {
    val state by viewModel.playerState.collectAsStateWithLifecycle()

    val duration = viewModel.player.duration.coerceAtLeast(minimumValue = 0L)
    var position by remember { mutableLongStateOf(value = 0L) }

    LaunchedEffect(Unit) {
        while (true) {
            position = viewModel.player.currentPosition
            delay(timeMillis = 200L)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .pointerInput(Unit) {
                    detectTapGestures {
                        viewModel.onIntent(PlayerIntent.ShowControlsTemporarily)
                    }
                },
        ) {

            PlayerSurface(
                player = viewModel.player,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
            )

            if (state.controlsVisible) {
                PlayerTime(
                    modifier = Modifier.align(Alignment.BottomStart),
                    position = position,
                    duration = duration
                )

                PlayerSlider(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    position = position,
                    duration = duration,
                ) { viewModel.player.seekTo(it) }

                PlayerControlButtons(
                    modifier = Modifier.align(Alignment.Center),
                    isPlaying = state.isPlaying,
                    onPlayPause = {
                        if (!state.isPlaying) {
                            viewModel.onIntent(PlayerIntent.Play(url = VIDEO_URL))
                        } else {
                            viewModel.onIntent(PlayerIntent.Pause)
                        }
                        viewModel.onIntent(PlayerIntent.ShowControlsTemporarily)
                    },
                    onRewind = {
                        viewModel.onIntent(PlayerIntent.Rewind)
                        viewModel.onIntent(PlayerIntent.ShowControlsTemporarily)
                    },
                    onForward = {
                        viewModel.onIntent(PlayerIntent.Forward)
                        viewModel.onIntent(PlayerIntent.ShowControlsTemporarily)
                    }
                )
            }
        }
    }
}
