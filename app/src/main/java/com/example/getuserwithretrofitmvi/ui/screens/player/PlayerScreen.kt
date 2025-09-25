package com.example.getuserwithretrofitmvi.ui.screens.player

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Forward10
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Replay10
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.media3.ui.compose.PlayerSurface
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlayerScreen(
    viewModel: PlayerViewModel = koinViewModel()
) {
    val state by viewModel.playerState.collectAsStateWithLifecycle()
    val url = "https://download.blender.org/durian/trailer/sintel_trailer-720p.mp4"

    val duration = viewModel.player.duration.coerceAtLeast(0L)
    var position by remember { mutableLongStateOf(0L) }

    LaunchedEffect(Unit) {
        while (true) {
            position = viewModel.player.currentPosition
            delay(200L)
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
                Text(
                    text = "${formatTime(position)} / ${formatTime(duration)}",
                    color = Color.White,
                    fontSize = 10.sp,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 16.dp, bottom = 58.dp)
                )


                Slider(
                    value = position.toFloat(),
                    valueRange = 0f..duration.toFloat(),
                    onValueChange = { viewModel.player.seekTo(it.toLong()) },
                    colors = SliderDefaults.colors(
                        thumbColor = Color.Blue,
                        activeTrackColor = Color.White,
                        inactiveTickColor = Color.Gray
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .align(Alignment.BottomCenter)
                )

                Row(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    IconButton(
                        onClick = {
                            viewModel.onIntent(PlayerIntent.Rewind)
                            viewModel.onIntent(PlayerIntent.ShowControlsTemporarily)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Replay10,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }

                    IconButton(
                        onClick = {
                            if (!state.isPlaying) {
                                viewModel.onIntent(PlayerIntent.Play(url))
                            } else {
                                viewModel.onIntent(PlayerIntent.Pause)
                            }
                            viewModel.onIntent(PlayerIntent.ShowControlsTemporarily)
                        }
                    ) {
                        Icon(
                            imageVector = if (!state.isPlaying) {
                                Icons.Filled.PlayArrow
                            } else {
                                Icons.Filled.Pause
                            },
                            contentDescription = null,
                            tint = Color.White
                        )
                    }

                    IconButton(
                        onClick = {
                            viewModel.onIntent(PlayerIntent.Forward)
                            viewModel.onIntent(PlayerIntent.ShowControlsTemporarily)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Forward10,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}

private fun formatTime(millis: Long): String {
    val totalSeconds = millis / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return "%02d:%02d".format(minutes, seconds)
}
