package com.example.getuserwithretrofitmvi.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.example.getuserwithretrofitmvi.ui.components.BottomNavigationBar
import com.example.getuserwithretrofitmvi.ui.screens.player.PlayerScreen
import com.example.getuserwithretrofitmvi.ui.screens.users.UserScreen

@Composable
fun AppRoot() {
    val topLevelBackStack = remember { TopLevelBackStack<Screen>(Screen.Users) }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                currentScreen = topLevelBackStack.topLevelKey,
                onItemSelected = { topLevelBackStack.switchTopLevel(it) }
            )
        }
    ) { paddingValues ->
        NavDisplay(
            modifier = Modifier.padding(paddingValues),
            backStack = topLevelBackStack.backStack,
            entryProvider = entryProvider {
                entry<Screen.Users> {
                    UserScreen()
                }
                entry<Screen.Player> {
                    PlayerScreen()
                }
            }
        )
    }
}
