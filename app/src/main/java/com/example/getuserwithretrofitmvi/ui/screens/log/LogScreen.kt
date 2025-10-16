package com.example.getuserwithretrofitmvi.ui.screens.log

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.getuserwithretrofitmvi.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun LogScreen(
    viewModel: LogViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Button(
            onClick = {
                viewModel.onIntent(LogIntent.CreateLog)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.create_log))
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            if (uiState.logs.isEmpty()) {
                item {
                    Box(modifier = Modifier.fillParentMaxSize()) {
                        Text(
                            text = stringResource(R.string.empty_list),
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            } else {
                items(items = uiState.logs.reversed()) { log ->
                    Text(text = log, modifier = Modifier.padding(vertical = 2.dp))
                }
            }

        }
    }
}
