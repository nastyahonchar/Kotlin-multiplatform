package com.example.kotlin_app.ui.screens.progress

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kotlin_app.ui.theme.AppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ProgressBarScreen() {

    var linearProgress by remember { mutableFloatStateOf(0f) }
    var loadingLinear by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

            Button(
                onClick = {
                    loadingLinear = true
                    linearProgress = 0f

                    scope.launch {
                        loadProgress { progress ->
                            linearProgress = progress
                        }
                        loadingLinear = false
                    }
                },
                enabled = !loadingLinear
            ) {
                Text("Start linear")
            }

            if (loadingLinear) {
                LinearProgressIndicator(
                    progress = { linearProgress },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

            var circularProgress by remember { mutableFloatStateOf(0f) }
            var loadingCircular by remember { mutableStateOf(false) }

            Button(
                onClick = {
                    loadingCircular = true
                    circularProgress = 0f

                    scope.launch {
                        loadProgress { progress ->
                            circularProgress = progress
                        }
                        loadingCircular = false
                    }
                },
                enabled = !loadingCircular
            ) {
                Text("Start circular determinate")
            }

            if (loadingCircular) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        progress = { circularProgress },
                        modifier = Modifier.size(64.dp)
                    )
                }
            }
        }

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

            var loadingLinearIndeterminate by remember { mutableStateOf(false) }
            val scope = rememberCoroutineScope()

            Button(
                onClick = {
                    loadingLinearIndeterminate = true

                    scope.launch {
                        delay(3000)
                        loadingLinearIndeterminate = false
                    }
                }
            ) {
                Text("Start linear indeterminate")
            }

            if (loadingLinearIndeterminate) {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

            var loadingCircularIndeterminate by remember { mutableStateOf(false) }
            val scope = rememberCoroutineScope()

            Button(
                onClick = {
                    loadingCircularIndeterminate = true

                    scope.launch {
                        delay(3000)
                        loadingCircularIndeterminate = false
                    }
                }
            ) {
                Text("Start circular indeterminate")
            }

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                if (loadingCircularIndeterminate) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(64.dp),
                        color = MaterialTheme.colorScheme.secondary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    )
                }
            }
        }
    }
}

suspend fun loadProgress(updateProgress: (Float) -> Unit) {
    for (i in 1..100) {
        updateProgress(i / 100f)
        delay(30)
    }
}

@Preview
@Composable
fun ProgressBarScreenPreview() {
    AppTheme {
        Scaffold {
            ProgressBarScreen()
        }
    }
}