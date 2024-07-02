package com.barrosedijanio.dreamcars.navigation.navGraph

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.barrosedijanio.dreamcars.navigation.screens.Screens
import com.barrosedijanio.dreamcars.ui.screens.LoginScreen
import com.barrosedijanio.dreamcars.ui.viewmodels.LoginViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.loginScreen(
    userLoggedIn: Boolean,
    signUp: () -> Unit,
    goToHome: () -> Unit
) {
    composable(Screens.Login.route) {
        val viewModel: LoginViewModel = koinViewModel()
        val loginState by viewModel.userResult.collectAsState()
        val uiState by viewModel.uiState.collectAsState()

        LaunchedEffect(key1 = userLoggedIn) {
            if (userLoggedIn) {
                goToHome()
            }
        }
        LoginScreen(
            uiState = uiState,
            loginState = loginState,
            goToHome = goToHome,
            onLogin = { userName ->
                viewModel.login(userName)
            },
            onSignIn = signUp
        )
    }
}