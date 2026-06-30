package com.futureguider.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.futureguider.domain.usecase.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AuthUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    val isLoggedIn = userRepository.isLoggedIn()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), false)

    val userName = userRepository.getUserName()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), "")

    fun register(name: String, email: String, password: String) {
        if (!validate(name, email, password)) return
        _uiState.update { it.copy(isLoading = true, errorMessage = null) }
        viewModelScope.launch {
            userRepository.register(name.trim(), email.trim(), password)
                .onSuccess { user ->
                    userRepository.saveSession(user)
                    _uiState.update { it.copy(isLoading = false, isSuccess = true) }
                }
                .onFailure { e ->
                    _uiState.update { it.copy(isLoading = false, errorMessage = e.message) }
                }
        }
    }

    fun login(email: String, password: String) {
        if (email.isBlank()) { _uiState.update { it.copy(errorMessage = "Email is required") }; return }
        if (password.isBlank()) { _uiState.update { it.copy(errorMessage = "Password is required") }; return }
        _uiState.update { it.copy(isLoading = true, errorMessage = null) }
        viewModelScope.launch {
            userRepository.login(email.trim(), password)
                .onSuccess { user ->
                    userRepository.saveSession(user)
                    _uiState.update { it.copy(isLoading = false, isSuccess = true) }
                }
                .onFailure { e ->
                    _uiState.update { it.copy(isLoading = false, errorMessage = e.message) }
                }
        }
    }

    fun logout() {
        viewModelScope.launch { userRepository.logout() }
    }

    fun clearError() = _uiState.update { it.copy(errorMessage = null) }

    private fun validate(name: String, email: String, password: String): Boolean {
        return when {
            name.isBlank()     -> { _uiState.update { it.copy(errorMessage = "Name is required") }; false }
            email.isBlank()    -> { _uiState.update { it.copy(errorMessage = "Email is required") }; false }
            !email.contains("@") -> { _uiState.update { it.copy(errorMessage = "Enter a valid email") }; false }
            password.length < 6 -> { _uiState.update { it.copy(errorMessage = "Password must be at least 6 characters") }; false }
            else -> true
        }
    }
}
