package com.example.getuserwithretrofitmvi.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.getuserwithretrofitmvi.ui.navigation.Screen

@Composable
fun BottomNavigationBar(
    currentScreen: Screen,
    onItemSelected: (Screen) -> Unit
) {
    val bottomNavItems = listOf(Screen.Users, Screen.Player, Screen.LogScreen)

    NavigationBar {
        bottomNavItems.forEach { item ->
            val selected = currentScreen == item
            NavigationBarItem(
                selected = selected,
                onClick = { onItemSelected(item) },
                icon = { Icon(imageVector = item.icon, contentDescription = null) },
                label = { Text(text = item.title) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Blue,
                    selectedTextColor = Color.Blue,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray
                )
            )
        }
    }
}
