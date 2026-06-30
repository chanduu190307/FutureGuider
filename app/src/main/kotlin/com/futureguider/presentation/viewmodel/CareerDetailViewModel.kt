package com.futureguider.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.futureguider.domain.model.CareerDetail
import com.futureguider.domain.usecase.CareerRepository
import com.futureguider.domain.usecase.SavedPathRepository
import com.futureguider.domain.usecase.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CareerDetailUiState(
    val careerName: String = "",
    val detail: CareerDetail? = null,
    val pathSteps: List<String> = emptyList(),
    val isLoading: Boolean = true,
    val isSaved: Boolean = false,
    val saveMessage: String? = null
)

@HiltViewModel
class CareerDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val careerRepository: CareerRepository,
    private val savedPathRepository: SavedPathRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val nodeId: Int = checkNotNull(savedStateHandle["nodeId"])
    private val nodeName: String = savedStateHandle["nodeName"] ?: ""

    private val _uiState = MutableStateFlow(CareerDetailUiState(careerName = nodeName))
    val uiState: StateFlow<CareerDetailUiState> = _uiState.asStateFlow()

    private val currentUserId: Flow<Int> = userRepository.getCurrentUserId()

    init {
        loadDetail()
        loadPath()
        checkIfSaved()
    }

    private fun loadDetail() {
        viewModelScope.launch {
            careerRepository.getCareerDetail(nodeId).collect { detail ->
                _uiState.update { it.copy(detail = detail, isLoading = false) }
            }
        }
    }

    private fun loadPath() {
        viewModelScope.launch {
            val path = careerRepository.buildPathToNode(nodeId)
            _uiState.update { it.copy(pathSteps = path) }
        }
    }

    private fun checkIfSaved() {
        viewModelScope.launch {
            currentUserId.firstOrNull()?.let { uid ->
                if (uid > 0) {
                    val saved = savedPathRepository.isAlreadySaved(uid, nodeId)
                    _uiState.update { it.copy(isSaved = saved) }
                }
            }
        }
    }

    fun saveCareerPath() {
        viewModelScope.launch {
            val uid = currentUserId.firstOrNull() ?: return@launch
            if (uid <= 0) {
                _uiState.update { it.copy(saveMessage = "Please log in to save paths") }
                return@launch
            }
            savedPathRepository.savePath(uid, nodeId)
                .onSuccess { _uiState.update { it.copy(isSaved = true, saveMessage = "Career path saved!") } }
                .onFailure { e -> _uiState.update { it.copy(saveMessage = e.message ?: "Failed to save") } }
        }
    }

    fun clearSaveMessage() = _uiState.update { it.copy(saveMessage = null) }
}
