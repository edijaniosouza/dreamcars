package com.barrosedijanio.dreamcars.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barrosedijanio.dreamcars.core.generic.GenericResult
import com.barrosedijanio.dreamcars.database.repositories.DatabaseRepository
import com.barrosedijanio.dreamcars.navigation.Session
import com.barrosedijanio.dreamcars.ui.state.LoginScreenUiState
import com.barrosedijanio.dreamcars.util.EmailValidator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val databaseRepository: DatabaseRepository,
) : ViewModel() {

    private var _uiState = MutableStateFlow(LoginScreenUiState())
    val uiState = _uiState.asStateFlow()

    private val _userResult = MutableStateFlow<GenericResult>(GenericResult.Empty)
    val userResult = _userResult.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onUsernameChange = { newValue ->
                    _uiState.update {
                        it.copy(userName = newValue)
                    }
                },
                onLoadingChange = { isLoading ->
                    _uiState.update {
                        it.copy(isLoading = isLoading)
                    }
                },
                onErrorChange = { error ->
                    _uiState.update {
                        it.copy(error = error)
                    }
                }
            )
        }
    }

    fun login(login: String) {
        _userResult.value = GenericResult.Loading
        val isEmail = EmailValidator(login).validateEmail()

        if (isEmail) {
            viewModelScope.launch {
                databaseRepository.loginWithEmail(login).collect{ result ->
                    _userResult.value = result
                }
            }
        } else {
            viewModelScope.launch {
                databaseRepository.loginWithUsername(login).collect{ result ->
                    _userResult.value = result
                }
            }
        }
    }
}