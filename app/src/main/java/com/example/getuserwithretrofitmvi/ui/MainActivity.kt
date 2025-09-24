package com.example.getuserwithretrofitmvi.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.getuserwithretrofitmvi.ui.screens.player.PlayerScreen
import com.example.getuserwithretrofitmvi.ui.theme.GetUserWithRetrofitMVITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GetUserWithRetrofitMVITheme {
                PlayerScreen()
            }
        }
    }
}

