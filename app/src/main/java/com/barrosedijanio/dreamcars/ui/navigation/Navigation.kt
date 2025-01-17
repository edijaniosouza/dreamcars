package com.barrosedijanio.dreamcars.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.barrosedijanio.dreamcars.ui.navigation.navGraph.homeScreen
import com.barrosedijanio.dreamcars.ui.navigation.navGraph.loginScreen
import com.barrosedijanio.dreamcars.ui.navigation.navGraph.signUpScreen
import com.barrosedijanio.dreamcars.ui.navigation.screens.Screens

@Composable
fun Navigation(userLoggedIn: Boolean) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.Home.route) {
        homeScreen(
            userLoggedIn = userLoggedIn,
            goToLoginScreen = { navController.navigate(Screens.Login.route) })
        loginScreen(
            userLoggedIn = userLoggedIn,
            goToHome = { navController.navigate(Screens.Home.route) },
            signUp = {
                navController.navigate(Screens.SignUp.route)
            })
        signUpScreen(onCancel = { navController.popBackStack() }) {
            navController.navigate(Screens.Home.route)
        }
    }
}