package com.barrosedijanio.dreamcars.di

import com.barrosedijanio.dreamcars.ui.viewmodels.HomeScreenViewModel
import com.barrosedijanio.dreamcars.ui.viewmodels.LoginViewModel
import com.barrosedijanio.dreamcars.ui.viewmodels.MainViewModel
import com.barrosedijanio.dreamcars.ui.viewmodels.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {

    viewModel<HomeScreenViewModel> {
        HomeScreenViewModel(get(), get(), get())
    }

    viewModel<LoginViewModel> {
        LoginViewModel(get(), get())
    }

    viewModel<SignUpViewModel> {
        SignUpViewModel(get())
    }

    viewModel<MainViewModel> {
        MainViewModel(get())
    }
}