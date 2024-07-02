package com.barrosedijanio.dreamcars.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barrosedijanio.dreamcars.R
import com.barrosedijanio.dreamcars.core.generic.GenericResult
import com.barrosedijanio.dreamcars.database.repositories.DatabaseRepository
import com.barrosedijanio.dreamcars.ui.state.SignUpUiState
import com.barrosedijanio.dreamcars.util.EmailValidator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val databaseRepository: DatabaseRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow(SignUpUiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onUsernameChange = { userName ->
                    _uiState.update {
                        it.copy(userName = userName)
                    }
                },
                onEmailChange = { email ->
                        _uiState.update {
                            it.copy(email = email)
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

    private val _userLogged = MutableStateFlow<GenericResult>(GenericResult.Empty)
    val userLogged = _userLogged.asStateFlow()

    fun signUp() {
        _userLogged.value = GenericResult.Loading
        if(_uiState.value.userName.isEmpty() || _uiState.value.userName.contains(" ")){
            _userLogged.value = GenericResult.Error(R.string.userName_invalid)
            return
        }
        if (!EmailValidator(_uiState.value.email).validateEmail()) {
            _userLogged.value = GenericResult.Error(R.string.invalid_email)
            return
        }

        viewModelScope.launch {
            _userLogged.value = databaseRepository.signUp(
                email = _uiState.value.email,
                userName = _uiState.value.userName
            )
        }
    }
}