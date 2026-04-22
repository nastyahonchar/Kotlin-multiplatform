package com.example.kotlin_app.ui.screens.timepicker

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlin_app.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialogScreen() {

    var isDialMode by remember { mutableStateOf(true) }

    var selectedHour by remember { mutableStateOf<Int?>(null) }
    var selectedMinute by remember { mutableStateOf<Int?>(null) }

    val timePickerState = rememberTimePickerState(
        initialHour = 12,
        initialMinute = 0,
        is24Hour = true
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { isDialMode = true }) {
                Text("Dial")
            }
            Button(onClick = { isDialMode = false }) {
                Text("Input")
            }
        }

        if (isDialMode) {
            val timePickerTypography = MaterialTheme.typography.copy(
                displayLarge = MaterialTheme.typography.displayLarge.copy(
                    fontSize = 45.sp,
                    letterSpacing = 0.sp
                )
            )

            MaterialTheme(typography = timePickerTypography) {
                TimePicker(state = timePickerState)
            }
        } else {
            TimeInput(state = timePickerState)
        }

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {

            Button(
                onClick = {
                    selectedHour = null
                    selectedMinute = null
                }
            ) {
                Text("Dismiss")
            }

            Button(
                onClick = {
                    selectedHour = timePickerState.hour
                    selectedMinute = timePickerState.minute
                }
            ) {
                Text("Confirm")
            }
        }

        if (selectedHour != null && selectedMinute != null) {
            Text(
                text = "Selected time: ${selectedHour.toString().padStart(2, '0')}:${selectedMinute.toString().padStart(2, '0')}"
            )
        } else {
            Text("No time selected")
        }
    }
}

@Preview
@Composable
fun TimePickerDialogScreenPreview() {
    AppTheme {
        Scaffold {
            TimePickerDialogScreen()
        }
    }
}