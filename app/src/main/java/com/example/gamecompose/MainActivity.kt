package com.example.gamecompose

import android.content.Context
import android.os.Bundle
import android.os.Vibrator
import android.os.VibrationEffect
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    private val vibrator by lazy {
        getSystemService(
            Context.VIBRATOR_SERVICE
        ) as Vibrator
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        super.onCreate(savedInstanceState)
        setContent {
            Home()
        }
    }

    private fun vibrate() {
        vibrator.vibrate(
            VibrationEffect.createOneShot(
                50, VibrationEffect.DEFAULT_AMPLITUDE
            )
        )
    }

    @Composable
    fun Home() {
        val loginViewModel = LoginViewModel()
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = "login",
        ) {
            composable("login") { LoginScreen(loginViewModel, navController) }
            composable("play") { PlayScreen(loginViewModel, navController) }
        }
    }

    @Composable
    fun LoginScreen(
        loginViewModel: LoginViewModel = viewModel(),
        navController: NavController,
    ) {
        val username: String by loginViewModel.username.observeAsState("")
        val password: String by loginViewModel.password.observeAsState("")
        val state: State by loginViewModel.state.observeAsState(State.NOTAUTHENTICATED)
        if (state == State.AUTHENTICATED) {
            navController.navigate("play")
        }
        LoginContent(
            username = username,
            password = password,
            onUsernameChange = { loginViewModel.onUsernameChange(it) },
            onPasswordChange = { loginViewModel.onPasswordChange(it) },
            loginFunc = { loginViewModel.login() },
            vibrate = { vibrate() }
        )
    }

    @Composable
    fun PlayScreen(
        loginViewModel: LoginViewModel = viewModel(),
        navController: NavController,
    ) {
        val state: State by loginViewModel.state.observeAsState(State.AUTHENTICATED)
        if (state == State.NOTAUTHENTICATED) {
            navController.navigate("login")
        }
        PlayContent(
            left = { loginViewModel.move(Direction.LEFT) },
            right = { loginViewModel.move(Direction.RIGHT) },
            up = { loginViewModel.move(Direction.UP) },
            down = { loginViewModel.move(Direction.DOWN) },
            logout = { loginViewModel.logout() },
            vibrate = { vibrate() }
        )
    }
}