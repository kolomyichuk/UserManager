package com.example.getuserwithretrofitmvi.ui.screens.player

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.getuserwithretrofitmvi.utils.FormatterUtils

@Composable
fun PlayerTime(
    modifier: Modifier = Modifier,
    position: Long,
    duration: Long
) {
    Text(
        text = "${FormatterUtils.formatTime(position)} / " +
                FormatterUtils.formatTime(duration),
        color = Color.White,
        fontSize = 10.sp,
        modifier = modifier
            .padding(start = 16.dp, bottom = 58.dp)
    )
}
