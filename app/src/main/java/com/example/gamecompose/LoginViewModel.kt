package com.example.gamecompose

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel() : ViewModel(), controller.AndroidCallback {
    private val _state: MutableLiveData<State> = MutableLiveData(State.NOTAUTHENTICATED)
    private val _username = MutableLiveData("")
    private val _password = MutableLiveData("")

    val state: LiveData<State> = _state
    val username: LiveData<String> = _username
    val password: LiveData<String> = _password

    init {
        controller.Controller.subscribeCallback(this)
    }

    fun onUsernameChange(username: String) {
        _username.value = username
    }

    fun onPasswordChange(password: String) {
        _password.value = password
    }

    fun login() {
        controller.Controller.run(username.value, password.value)
    }

    fun logout() {
        _state.value = State.NOTAUTHENTICATED
    }

    fun move(direction: Direction) {
        when (direction) {
            Direction.LEFT -> controller.Controller.move(controller.Controller.LEFT)
            Direction.RIGHT -> controller.Controller.move(controller.Controller.RIGHT)
            Direction.UP -> controller.Controller.move(controller.Controller.UP)
            Direction.DOWN -> controller.Controller.move(controller.Controller.DOWN)
        }
    }

    override fun error(p0: String?) {
        _state.value = State.NOTAUTHENTICATED
    }

    override fun ready() {
        _state.value = State.AUTHENTICATED
    }

}
