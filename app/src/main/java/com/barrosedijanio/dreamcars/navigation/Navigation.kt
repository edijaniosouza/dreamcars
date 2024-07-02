package com.barrosedijanio.dreamcars.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.barrosedijanio.dreamcars.navigation.navGraph.homeScreen
import com.barrosedijanio.dreamcars.navigation.navGraph.loginScreen
import com.barrosedijanio.dreamcars.navigation.navGraph.signUpScreen
import com.barrosedijanio.dreamcars.navigation.screens.Screens

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.Login.route) {
        homeScreen()
        loginScreen(
            goToHome = { navController.navigate(Screens.Home.route) },
            signUp = { navController.navigate(Screens.SignUp.route) })
        signUpScreen(onCancel = {navController.popBackStack()}){
            navController.navigate(Screens.Home.route)
        }
    }
}