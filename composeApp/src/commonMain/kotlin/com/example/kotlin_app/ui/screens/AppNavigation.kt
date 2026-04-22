package com.example.kotlin_app.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinapp.composeapp.generated.resources.*
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import com.example.kotlin_app.ui.screens.buttons.ButtonsScreen
import com.example.kotlin_app.ui.screens.checkboxes.CheckboxesScreen
import com.example.kotlin_app.ui.screens.chips.ChipsScreen
import com.example.kotlin_app.ui.screens.datepicker.DatepickerDialogScreen
import com.example.kotlin_app.ui.screens.dialog.DialogScreen
import com.example.kotlin_app.ui.screens.divider.DividerScreen
import com.example.kotlin_app.ui.screens.progress.ProgressBarScreen
import com.example.kotlin_app.ui.screens.radio.RadioButtonsScreen
import com.example.kotlin_app.ui.screens.switch.SwitchScreen
import com.example.kotlin_app.ui.screens.timepicker.TimePickerDialogScreen
import com.example.kotlin_app.ui.screens.main.MainScreen
import kotlinx.coroutines.launch

enum class AppScreen(val title: StringResource) {
    Main(title = Res.string.main),
    Buttons(title = Res.string.buttons),
    Checkboxes(title = Res.string.checkboxes),
    Chips(title = Res.string.chips),
    DatepickerDialog(title = Res.string.datepicker),
    Dialog(title = Res.string.dialog),
    Divider(title = Res.string.divider),
    ProgressBar(title = Res.string.progress),
    RadioButtons(title = Res.string.radio),
    Switch(title = Res.string.switch),
    TimepickerDialog(title = Res.string.timepicker),
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    currentScreen: AppScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(Res.string.back)
                    )
                }
            }
        })
}

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = AppScreen.valueOf(
        backStackEntry?.destination?.route ?: AppScreen.Main.name
    )

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            AppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() })
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = AppScreen.Main.name,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            composable(route = AppScreen.Main.name) {
                MainScreen(
                    onButtonsClicked = {navController.navigate(AppScreen.Buttons.name)},
                    onCheckboxesClicked = {navController.navigate(AppScreen.Checkboxes.name)},
                    onChipsClicked = {navController.navigate(AppScreen.Chips.name)},
                    onDatepickerDialogClicked = {navController.navigate(AppScreen.DatepickerDialog.name)},
                    onDialogClicked = {navController.navigate(AppScreen.Dialog.name)},
                    onDividerClicked = {navController.navigate(AppScreen.Divider.name)},
                    onProgressBarClicked = {navController.navigate(AppScreen.ProgressBar.name)},
                    onRadioButtonsClicked = {navController.navigate(AppScreen.RadioButtons.name)},
                    onSwitchClicked = {navController.navigate(AppScreen.Switch.name)},
                    onTimepickerDialogClicked = {navController.navigate(AppScreen.TimepickerDialog.name)},
                )
            }
            composable(route = AppScreen.Buttons.name) {
                ButtonsScreen(
                    onFilledButtonClicked = { text ->
                        scope.launch {
                            snackbarHostState
                                .showSnackbar(
                                    message = text,
                                    duration = SnackbarDuration.Short
                                )
                        }
                    }
                )
            }

            composable(route = AppScreen.Checkboxes.name) {
                CheckboxesScreen()
            }

            composable(route = AppScreen.Chips.name) {
                ChipsScreen(
                    text = "Hello",
                    onDismiss = {}
                )
            }

            composable(route = AppScreen.DatepickerDialog.name) {
                DatepickerDialogScreen()
            }

            composable(route = AppScreen.Dialog.name) {
                DialogScreen()
            }

            composable(route = AppScreen.Divider.name) {
                DividerScreen()
            }

            composable(route = AppScreen.ProgressBar.name) {
                ProgressBarScreen()
            }

            composable(route = AppScreen.RadioButtons.name) {
                RadioButtonsScreen()
            }

            composable(route = AppScreen.Switch.name) {
                SwitchScreen()
            }

            composable(route = AppScreen.TimepickerDialog.name) {
                TimePickerDialogScreen()
            }
        }
    }
}