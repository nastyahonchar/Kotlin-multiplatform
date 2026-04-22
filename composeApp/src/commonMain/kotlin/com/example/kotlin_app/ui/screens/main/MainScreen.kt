package com.example.kotlin_app.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinapp.composeapp.generated.resources.Res
import kotlinapp.composeapp.generated.resources.buttons
import kotlinapp.composeapp.generated.resources.checkboxes
import kotlinapp.composeapp.generated.resources.chips
import kotlinapp.composeapp.generated.resources.datepicker
import kotlinapp.composeapp.generated.resources.dialog
import kotlinapp.composeapp.generated.resources.divider
import kotlinapp.composeapp.generated.resources.progress
import kotlinapp.composeapp.generated.resources.radio
import kotlinapp.composeapp.generated.resources.switch
import kotlinapp.composeapp.generated.resources.timepicker
import org.jetbrains.compose.resources.stringResource

@Composable
fun MainScreen(
    onButtonsClicked: () -> Unit,
    onCheckboxesClicked: () -> Unit,
    onChipsClicked: () -> Unit,
    onDatepickerDialogClicked: () -> Unit,
    onDialogClicked: () -> Unit,
    onDividerClicked: () -> Unit,
    onProgressBarClicked: () -> Unit,
    onRadioButtonsClicked: () -> Unit,
    onSwitchClicked: () -> Unit,
    onTimepickerDialogClicked: () -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onButtonsClicked()
            }
        ) {
            Text(stringResource(Res.string.buttons))
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onCheckboxesClicked()
            }
        ) {
            Text(stringResource(Res.string.checkboxes))
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onChipsClicked()
            }
        ) {
            Text(stringResource(Res.string.chips))
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onDatepickerDialogClicked()
            }
        ) {
            Text(stringResource(Res.string.datepicker))
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onDialogClicked()
            }
        ) {
            Text(stringResource(Res.string.dialog))
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onDividerClicked()
            }
        ) {
            Text(stringResource(Res.string.divider))
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onProgressBarClicked()
            }
        ) {
            Text(stringResource(Res.string.progress))
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onRadioButtonsClicked()
            }
        ) {
            Text(stringResource(Res.string.radio))
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onSwitchClicked()
            }
        ) {
            Text(stringResource(Res.string.switch))
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onTimepickerDialogClicked()
            }
        ) {
            Text(stringResource(Res.string.timepicker))
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen(
        {},
        {},
        {},
        {},
        {},
        {},
        {},
        {},
        {},
        {}
    )
}