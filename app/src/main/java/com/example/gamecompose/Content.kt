package com.example.gamecompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PlayContent(
    left: () -> Unit,
    right: () -> Unit,
    up: () -> Unit,
    down: () -> Unit,
    logout: () -> Unit,
    vibrate: () -> Unit,
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            OutlinedButton(
                onClick = {
                    vibrate()
                    logout()
                }
            ) {
                Text(text = "logout")
            }
        }
        Row(
            Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Button(
                onClick = {
                    vibrate()
                    left()
                }
            ) {
                Text(text = "L")
            }
            Button(
                onClick = {
                    vibrate()
                    down()
                }
            ) {
                Text(text = "D")
            }
            Button(
                onClick = {
                    vibrate()
                    up()
                }
            ) {
                Text(text = "U")
            }
            Button(
                onClick = {
                    vibrate()
                    right()
                }
            ) {
                Text(text = "R")
            }
        }
    }
}

@Composable
fun LoginContent(
    username: String,
    password: String,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    loginFunc: () -> Unit,
    vibrate: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        OutlinedTextField(
            value = username,
            onValueChange = onUsernameChange,
            label = { Text(text = "Username") },
            singleLine = true
        )
        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = { Text(text = "Password") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            contentAlignment = Alignment.Center

        ) {
            Button(
                onClick = {
                    vibrate()
                    loginFunc()
                },
                Modifier.size(100.dp,50.dp)
            ) {
                Text("login")
            }
        }
    }
}
