package com.barrosedijanio.dreamcars.ui.state

data class SignUpUiState(
    var userName: String = "",
    val onUsernameChange: (String) -> Unit = {},
    var email: String = "",
    val onEmailChange: (String) -> Unit = {},
    var isLoading: Boolean = false,
    val onLoadingChange : (Boolean) -> Unit = {},
    var error: Int = 0,
    val onErrorChange : (Int) -> Unit = {}
)