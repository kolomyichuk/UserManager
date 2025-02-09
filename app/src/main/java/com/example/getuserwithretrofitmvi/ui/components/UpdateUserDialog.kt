package com.example.getuserwithretrofitmvi.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.getuserwithretrofitmvi.R
import com.example.getuserwithretrofitmvi.data.model.User

@Composable
fun UpdateUserDialog(
    user: User,
    onDismiss: () -> Unit,
    onUpdate: (String, String) -> Unit
) {
    var firstName by remember {
        mutableStateOf(user.firstName)
    }
    var lastName by remember {
        mutableStateOf(user.lastName)
    }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { stringResource(R.string.update_user) },
        text = {
            Column {
                OutlinedTextField(value = firstName, onValueChange = { firstName = it }, label = {
                    Text(
                        text = "Firstname"
                    )
                })
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(value = lastName, onValueChange = { lastName = it }, label = {
                    Text(
                        text = "Lastname"
                    )
                })
            }
        },
        confirmButton = {
            Button(onClick = { onUpdate(firstName, lastName) }) {
                Text(text = "Update")
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) {
                Text(text = "Cancel")
            }
        })
}