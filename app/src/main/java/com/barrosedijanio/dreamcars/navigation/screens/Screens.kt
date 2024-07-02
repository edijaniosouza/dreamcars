package com.barrosedijanio.dreamcars.navigation.screens

sealed class Screens(val route: String) {
    data object Home: Screens("home")
    data object Login: Screens("login")
    data object SignUp: Screens("sign_up")
    data object FavoriteCars: Screens("favorite_cars")
}