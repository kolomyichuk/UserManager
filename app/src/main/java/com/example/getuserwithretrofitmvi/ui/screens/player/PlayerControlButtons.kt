package com.example.getuserwithretrofitmvi.ui.screens.player

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Forward10
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Replay10
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PlayerControlButtons(
    modifier: Modifier = Modifier,
    isPlaying: Boolean,
    onPlayPause: () -> Unit,
    onRewind: () -> Unit,
    onForward: () -> Unit
) {
    Row(
        modifier = modifier.padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        IconButton(
            onClick = onRewind
        ) {
            Icon(
                imageVector = Icons.Filled.Replay10,
                contentDescription = null,
                tint = Color.White
            )
        }

        IconButton(
            onClick = onPlayPause
        ) {
            Icon(
                imageVector = if (!isPlaying) {
                    Icons.Filled.PlayArrow
                } else {
                    Icons.Filled.Pause
                },
                contentDescription = null,
                tint = Color.White
            )
        }

        IconButton(
            onClick = onForward
        ) {
            Icon(
                imageVector = Icons.Filled.Forward10,
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}
