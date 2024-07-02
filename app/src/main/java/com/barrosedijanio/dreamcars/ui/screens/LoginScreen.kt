package com.barrosedijanio.dreamcars.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barrosedijanio.dreamcars.R
import com.barrosedijanio.dreamcars.core.generic.GenericResult
import com.barrosedijanio.dreamcars.ui.state.LoginScreenUiState

@Composable
fun LoginScreen(
    uiState: LoginScreenUiState,
    loginState: GenericResult,
    onLogin: (String) -> Unit,
    onSignIn: () -> Unit,
    goToHome: () -> Unit
) {

    LaunchedEffect(key1 = loginState) {
        when (loginState) {
            is GenericResult.Loading -> {
                uiState.onLoadingChange(true)
            }

            is GenericResult.Success -> {
                uiState.onLoadingChange(false)
                goToHome()
            }

            is GenericResult.Error -> {
                uiState.onLoadingChange(false)
                uiState.onErrorChange(loginState.errorMessage)
            }

            else -> Unit
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(R.string.insert_your_username_or_email),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
        )
        OutlinedTextField(
            value = uiState.userName,
            onValueChange = {
                uiState.onUsernameChange(it)
            },
            placeholder = { Text(stringResource(R.string.user_or_email)) },
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = stringResource(R.string.login)
                )
            }
        )

        if (uiState.error != 0) Text(
            stringResource(id = uiState.error),
            fontSize = 14.sp,
            color = Red,
            fontWeight = FontWeight.Bold
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator()
            } else {
                ElevatedButton(
                    shape = ShapeDefaults.Medium,
                    enabled = uiState.userName.isNotEmpty(),
                    onClick = { onLogin(uiState.userName) }
                ) {
                    Text(text = stringResource(R.string.sign_in))
                }

                TextButton(onClick = onSignIn) {
                    Text(stringResource(id = R.string.sign_up))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
}