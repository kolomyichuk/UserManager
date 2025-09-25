package com.example.getuserwithretrofitmvi.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.getuserwithretrofitmvi.ui.navigation.Screen

@Composable
fun BottomNavigationBar(
    currentScreen: Screen,
    onItemSelected: (Screen) -> Unit
) {
    val bottomNavItems = listOf(Screen.Users, Screen.Player)

    NavigationBar {
        bottomNavItems.forEach { item ->
            val selected = currentScreen == item
            NavigationBarItem(
                selected = selected,
                onClick = { onItemSelected(item) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null,
                        tint = Color.Blue
                    )
                },
                label = {
                    Text(text = item.title, color = Color.Blue)
                },
            )
        }
    }
}
