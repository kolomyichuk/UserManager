package com.example.getuserwithretrofitmvi.ui.screens.player

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PlayerSlider(
    modifier: Modifier = Modifier,
    position: Long,
    duration: Long,
    onSeek: (Long) -> Unit
) {
    Slider(
        value = position.toFloat(),
        valueRange = 0f..duration.toFloat(),
        onValueChange = { onSeek(it.toLong()) },
        colors = SliderDefaults.colors(
            thumbColor = Color.Blue,
            activeTrackColor = Color.White,
            inactiveTickColor = Color.Gray
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}
