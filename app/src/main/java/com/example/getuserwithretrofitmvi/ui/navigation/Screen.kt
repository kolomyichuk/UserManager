package com.example.getuserwithretrofitmvi.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayLesson
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey
import com.example.getuserwithretrofitmvi.R
import kotlinx.serialization.Serializable

sealed interface Screen : NavKey {

    @Serializable
    data object Users : Screen, BottomNavItem {
        override val icon: ImageVector = Icons.Filled.Person
        override val title: String = "Users"

    }

    @Serializable
    data object Player : Screen, BottomNavItem {
        override val icon: ImageVector = Icons.Filled.PlayLesson
        override val title: String = "Player"
    }

    @Serializable
    data object LogScreen : Screen, BottomNavItem{
        override val title: String = "Log"
        override val icon: ImageVector = Icons.AutoMirrored.Default.List
    }
}
