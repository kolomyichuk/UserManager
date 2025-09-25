package com.example.getuserwithretrofitmvi.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.example.getuserwithretrofitmvi.ui.navigation.Screen
import com.example.getuserwithretrofitmvi.ui.navigation.TopLevelBackStack
import com.example.getuserwithretrofitmvi.ui.screens.player.PlayerScreen
import com.example.getuserwithretrofitmvi.ui.screens.users.UserScreen
import com.example.getuserwithretrofitmvi.ui.theme.GetUserWithRetrofitMVITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GetUserWithRetrofitMVITheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    val bottomNavItems = listOf(Screen.Users, Screen.Player)
    val backStack = remember { mutableStateListOf<Any>(Screen.Users) }
    val topLevelBackStack = remember { TopLevelBackStack<NavKey>(Screen.Users) }
    Scaffold(
        bottomBar = {
            NavigationBar {
                bottomNavItems.forEach { item ->
                    val selected = topLevelBackStack.topLevelKey == item
                    NavigationBarItem(
                        selected = selected,
                        onClick = { topLevelBackStack.switchTopLevel(item) },
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
    ) { paddingValues ->

        NavDisplay(
            modifier = Modifier.padding(paddingValues),
            backStack = backStack,
            entryProvider = entryProvider {
                entry<Screen.Users> {
                    UserScreen()
                }
                entry<Screen.Player> {
                    PlayerScreen()
                }
            },
            onBack = {
                backStack.removeLastOrNull()
            }
        )
    }
}