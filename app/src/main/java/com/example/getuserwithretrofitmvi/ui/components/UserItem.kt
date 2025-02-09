package com.example.getuserwithretrofitmvi.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.getuserwithretrofitmvi.data.model.User

@Composable
fun UserItem(user: User, onDelete: () -> Unit, onUpdate: (String, String) -> Unit) {
    var showDialog by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "${user.firstName}, ${user.lastName} ")

            Spacer(modifier = Modifier.weight(1f))

            IconButton(onClick = { showDialog = true }) {
                Icon(imageVector = Icons.Rounded.Edit, contentDescription = null)
            }

            IconButton(onClick = { onDelete() }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            }
        }
    }

    if (showDialog) {
        UpdateUserDialog(
            user = user,
            onDismiss = { showDialog = false },
            onUpdate = { firstName, lastName ->
                onUpdate(firstName, lastName)
                showDialog = false
            })
    }
}