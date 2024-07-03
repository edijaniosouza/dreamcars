package com.barrosedijanio.dreamcars.ui.navigation.navGraph

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.barrosedijanio.dreamcars.ui.navigation.screens.Screens
import com.barrosedijanio.dreamcars.ui.screens.SignUpScreen
import com.barrosedijanio.dreamcars.ui.viewmodels.SignUpViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.signUpScreen(
    onCancel: () -> Unit,
    goToHome: () -> Unit
) {
    composable(Screens.SignUp.route) {
        val viewModel: SignUpViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()
        val signUpState by viewModel.userLogged.collectAsState()

        SignUpScreen(
            uiState = uiState,
            signUpState = signUpState,
            onCancel = onCancel,
            onSignUp = {
                viewModel.signUp()
            },
            goToHome = goToHome
        )
    }
}