package com.example.getuserwithretrofitmvi.ui.screens.users

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.getuserwithretrofitmvi.ui.components.ShowCircularProgressIndicator
import org.koin.androidx.compose.koinViewModel

@Composable
fun UserScreen(viewModel: UserViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()
    var searchQuery by remember {
        mutableStateOf("")
    }

    LaunchedEffect(Unit) {
        viewModel.dispatch(UserIntent.LoadUsers)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text(text = "Search User") })

        Spacer(modifier = Modifier.height(8.dp))

        when (state) {
            is UserState.Loading -> ShowCircularProgressIndicator()
            is UserState.Success -> {
                val users = (state as UserState.Success).users.filter {
                    it.firstName.contains(
                        searchQuery,
                        ignoreCase = true
                    )
                }
                LazyColumn {
                    items(users) { user ->
                        UserItem(
                            user,
                            onDelete = { viewModel.dispatch(UserIntent.DeleteUser(user.id)) },
                            onUpdate = { firstName, lastName ->
                                val updatedUser = user.copy(firstName = firstName, lastName = lastName)
                                viewModel.dispatch(UserIntent.UpdateUser(user.id, updatedUser))
                            }
                        )
                    }
                }
            }

            is UserState.Error -> Text(text = "Error: ${(state as UserState.Error).message}")
        }
    }
}