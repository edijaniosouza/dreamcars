package com.barrosedijanio.dreamcars.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barrosedijanio.dreamcars.core.generic.GenericResult
import com.barrosedijanio.dreamcars.database.repositories.DatabaseRepository
import com.barrosedijanio.dreamcars.navigation.Session
import com.barrosedijanio.dreamcars.service.model.Car
import com.barrosedijanio.dreamcars.ui.state.LoginScreenUiState
import com.barrosedijanio.dreamcars.util.EmailValidator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val session: Session
) : ViewModel() {

    private val _userSession = MutableStateFlow<Boolean>(false)
    val userSession = session.isUserLoggedIn().stateIn(
        scope = viewModelScope, started = SharingStarted.Eagerly, initialValue = false
    )

    init {
        isUserLoggedIn()
    }

    private fun isUserLoggedIn() {
        viewModelScope.launch {
            _userSession.value = session.isUserLoggedIn().first()
        }
    }
}