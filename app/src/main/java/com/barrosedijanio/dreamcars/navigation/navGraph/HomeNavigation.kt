package com.barrosedijanio.dreamcars.navigation.navGraph

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.barrosedijanio.dreamcars.navigation.screens.Screens
import com.barrosedijanio.dreamcars.ui.screens.HomeScreen
import com.barrosedijanio.dreamcars.ui.viewmodels.HomeScreenViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.homeScreen(
) {
    composable(route = Screens.Home.route) {
        val viewModel: HomeScreenViewModel = koinViewModel();
        val data by viewModel.data.collectAsState()
        val favoriteData by viewModel.favoriteData.collectAsState()

        LaunchedEffect(key1 = Unit) {
            viewModel.loadData()
        }

        HomeScreen(data = data, favoriteData = favoriteData) { carSelected, isFav ->
            viewModel.favoriteItem(carSelected, isFav)
        }
    }
}