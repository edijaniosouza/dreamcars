package com.barrosedijanio.dreamcars.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
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
import com.barrosedijanio.dreamcars.ui.state.SignUpUiState

@Composable
fun SignUpScreen(
    uiState: SignUpUiState,
    signUpState: GenericResult,
    onSignUp: () -> Unit,
    onCancel: () -> Unit,
    goToHome: () -> Unit
) {

    LaunchedEffect(key1 = signUpState) {
        when (signUpState) {
            is GenericResult.Loading -> {
                uiState.onLoadingChange(true)
            }

            is GenericResult.Success -> {
                uiState.onLoadingChange(false)
                goToHome()
            }

            is GenericResult.Error -> {
                uiState.onLoadingChange(false)
                uiState.onErrorChange(signUpState.errorMessage)
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
            text = stringResource(R.string.insert_your_username),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
        )
        OutlinedTextField(
            value = uiState.userName,
            onValueChange = {
                uiState.onUsernameChange(it)
            },
            placeholder = { Text(stringResource(R.string.user_placeholder)) },
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = stringResource(R.string.user)
                )
            }
        )

        Text(
            text = stringResource(R.string.insert_your_email),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
        )
        OutlinedTextField(
            value = uiState.email,
            onValueChange = {
                uiState.onEmailChange(it)
            },
            placeholder = { Text(stringResource(R.string.email_placeholder)) },
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = stringResource(R.string.email)
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
            ElevatedButton(
                shape = ShapeDefaults.Medium,
                enabled = !uiState.isLoading && uiState.userName.isNotEmpty(),
                onClick = onSignUp
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator()
                } else {
                    Text(text = stringResource(R.string.sign_in))
                }
            }

            TextButton(onClick = onCancel) {
                Text(stringResource(R.string.cancel))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen(
        uiState = SignUpUiState(),
        signUpState = GenericResult.Empty,
        onSignUp = {},
        onCancel = {},
        goToHome = {})
}