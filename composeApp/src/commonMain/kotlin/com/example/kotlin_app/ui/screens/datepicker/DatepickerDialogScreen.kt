package com.example.kotlin_app.ui.screens.datepicker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.example.kotlin_app.ui.theme.AppTheme
import kotlinx.datetime.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatepickerDialogScreen() {

    var showDocked by remember { mutableStateOf(false) }
    var showModal by remember { mutableStateOf(false) }
    var showModalInput by remember { mutableStateOf(false) }
    var showRange by remember { mutableStateOf(false) }

    var selectedDate by remember { mutableStateOf<Long?>(null) }
    var selectedRange by remember { mutableStateOf<Pair<Long?, Long?>>(Pair(null, null)) }

    val datePickerState = rememberDatePickerState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        Text("1. Docked DatePicker")

        Box {
            OutlinedTextField(
                value = selectedDate?.let { formatDate(it) } ?: "",
                onValueChange = {},
                label = { Text("DOB") },
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { showDocked = !showDocked }) {
                        Icon(Icons.Default.DateRange, null)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            if (showDocked) {
                Popup(
                    onDismissRequest = { showDocked = false },
                    alignment = Alignment.TopStart
                ) {
                    Box(
                        modifier = Modifier
                            .padding(top = 72.dp)
                            .background(MaterialTheme.colorScheme.surface)
                            .padding(16.dp)
                    ) {
                        DatePicker(
                            state = datePickerState,
                            showModeToggle = false
                        )

                        LaunchedEffect(datePickerState.selectedDateMillis) {
                            selectedDate = datePickerState.selectedDateMillis
                        }
                    }
                }
            }
        }

        Text("2. Modal DatePicker")

        Button(onClick = { showModal = true }) {
            Text("Open modal")
        }

        if (showModal) {
            DatePickerDialog(
                onDismissRequest = { showModal = false },
                confirmButton = {
                    TextButton(onClick = {
                        selectedDate = datePickerState.selectedDateMillis
                        showModal = false
                    }) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showModal = false }) {
                        Text("Cancel")
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }

        Text("3. Modal Input DatePicker")

        Button(onClick = { showModalInput = true }) {
            Text("Open input modal")
        }

        if (showModalInput) {
            val inputState = rememberDatePickerState(
                initialDisplayMode = DisplayMode.Input
            )

            DatePickerDialog(
                onDismissRequest = { showModalInput = false },
                confirmButton = {
                    TextButton(onClick = {
                        selectedDate = inputState.selectedDateMillis
                        showModalInput = false
                    }) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showModalInput = false }) {
                        Text("Cancel")
                    }
                }
            ) {
                DatePicker(state = inputState)
            }
        }

        Text("4. Range DatePicker")

        Button(onClick = { showRange = true }) {
            Text("Select range")
        }

        if (showRange) {
            val rangeState = rememberDateRangePickerState()

            DatePickerDialog(
                onDismissRequest = { showRange = false },
                confirmButton = {
                    TextButton(onClick = {
                        selectedRange = Pair(
                            rangeState.selectedStartDateMillis,
                            rangeState.selectedEndDateMillis
                        )
                        showRange = false
                    }) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showRange = false }) {
                        Text("Cancel")
                    }
                }
            ) {
                DateRangePicker(
                    state = rangeState,
                    showModeToggle = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(500.dp)
                )
            }
        }

        Text("Selected date: ${selectedDate?.let { formatDate(it) } ?: "None"}")

        Text(
            "Range: ${
                if (selectedRange.first != null && selectedRange.second != null)
                    "${formatDate(selectedRange.first!!)} - ${formatDate(selectedRange.second!!)}"
                else "None"
            }"
        )
    }
}

fun formatDate(millis: Long): String {
    val date = Instant.fromEpochMilliseconds(millis)
        .toLocalDateTime(TimeZone.currentSystemDefault())
        .date

    return "${date.day}/${date.month}/${date.year}"
}

@Preview
@Composable
fun DatepickerDialogScreenPreview() {
    AppTheme {
        Scaffold {
            DatepickerDialogScreen()
        }
    }
}