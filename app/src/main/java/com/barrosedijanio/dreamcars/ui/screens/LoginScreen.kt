package com.barrosedijanio.dreamcars.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barrosedijanio.dreamcars.R
import com.barrosedijanio.dreamcars.core.generic.GenericResult
import com.barrosedijanio.dreamcars.ui.state.LoginScreenUiState
import com.barrosedijanio.dreamcars.ui.theme.poppinsFontFamily

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
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

//        Card(
//            modifier = Modifier.size(120.dp),
//            colors = CardDefaults.cardColors(
//                containerColor = Color.Red
//            )
//        ) {
//            Column(
//                modifier = Modifier.fillMaxSize(),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Icon(
////                    modifier = Modifier.size(50.dp),
//                    painter = painterResource(id = R.drawable.dreamcars),
////                    tint = Color.White,
//                    contentDescription = null
//                )
//                Text(
//                    modifier = Modifier.padding(10.dp),
//                    text = stringResource(R.string.app_name),
//                    color = Color.White,
//                    fontSize = 20.sp,
//                    fontWeight = FontWeight.SemiBold,
//                    fontFamily = poppinsFontFamily
//                )
//            }
//        }

        Icon(
            modifier = Modifier.size(150.dp),
            painter = painterResource(id = R.drawable.dreamcars),
            tint = Color.Unspecified,
            contentDescription = null
        )

        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = stringResource(R.string.userName),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = poppinsFontFamily
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

        Spacer(modifier = Modifier.padding(50.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
}