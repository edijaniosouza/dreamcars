package com.barrosedijanio.dreamcars.ui.navigation.navGraph

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.barrosedijanio.dreamcars.ui.navigation.screens.Screens
import com.barrosedijanio.dreamcars.ui.screens.HomeScreen
import com.barrosedijanio.dreamcars.ui.viewmodels.HomeScreenViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.homeScreen(
    userLoggedIn: Boolean,
    goToLoginScreen: () -> Unit
) {
    composable(route = Screens.Home.route) {
        val viewModel: HomeScreenViewModel = koinViewModel()
        val data by viewModel.data.collectAsState()
        val favoriteData by viewModel.favoriteData.collectAsState()

        LaunchedEffect(key1 = userLoggedIn) {
            if(!userLoggedIn){
                goToLoginScreen()
            }
        }

        HomeScreen(data = data, favoriteData = favoriteData, onExitApp = {
            viewModel.logout()
            goToLoginScreen()
        }) { carSelected, isFav ->
            viewModel.toggleFavorite(carSelected, isFav)
        }
    }
}