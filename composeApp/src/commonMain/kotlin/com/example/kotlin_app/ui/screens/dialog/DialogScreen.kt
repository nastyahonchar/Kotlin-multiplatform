package com.example.kotlin_app.ui.screens.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kotlin_app.ui.theme.AppTheme
import androidx.compose.ui.window.Dialog
import org.jetbrains.compose.resources.painterResource
import kotlinapp.composeapp.generated.resources.Res
import kotlinapp.composeapp.generated.resources.mountain

@Composable
fun DialogScreen() {

    var showAlert by remember { mutableStateOf(false) }
    var showMinimal by remember { mutableStateOf(false) }
    var showImage by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Button(onClick = { showAlert = true }) {
            Text("Alert Dialog")
        }

        Button(onClick = { showMinimal = true }) {
            Text("Minimal Dialog")
        }

        Button(onClick = { showImage = true }) {
            Text("Image Dialog")
        }
    }

    if (showAlert) {
        AlertDialog(
            onDismissRequest = { showAlert = false },
            icon = {
                Icon(Icons.Default.Info, contentDescription = null)
            },
            title = {
                Text("Alert Dialog")
            },
            text = {
                Text("This is a standard AlertDialog example")
            },
            confirmButton = {
                TextButton(onClick = { showAlert = false }) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = { showAlert = false }) {
                    Text("Dismiss")
                }
            }
        )
    }

    if (showMinimal) {
        Dialog(onDismissRequest = { showMinimal = false }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Minimal Dialog")
                        Spacer(Modifier.height(12.dp))
                        Button(onClick = { showMinimal = false }) {
                            Text("Close")
                        }
                    }
                }
            }
        }
    }

    if (showImage) {
        Dialog(onDismissRequest = { showImage = false }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(380.dp)
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Image(
                        painter = painterResource(Res.drawable.mountain),
                        contentDescription = "Mountain",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(160.dp)
                            .width(200.dp)
                    )

                    Text(
                        text = "Dialog with image + buttons",
                        modifier = Modifier.padding(16.dp),
                        textAlign = TextAlign.Center
                    )

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextButton(onClick = { showImage = false }) {
                            Text("Dismiss")
                        }
                        TextButton(onClick = { showImage = false }) {
                            Text("Confirm")
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DialogScreenPreview() {
    AppTheme {
        DialogScreen()
    }
}