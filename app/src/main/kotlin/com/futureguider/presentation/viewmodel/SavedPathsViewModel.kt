package com.futureguider.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.futureguider.domain.model.SavedPath
import com.futureguider.domain.usecase.SavedPathRepository
import com.futureguider.domain.usecase.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedPathsViewModel @Inject constructor(
    private val savedPathRepository: SavedPathRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _paths = MutableStateFlow<List<SavedPath>>(emptyList())
    val paths: StateFlow<List<SavedPath>> = _paths.asStateFlow()

    val userName = userRepository.getUserName()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), "")

    init {
        viewModelScope.launch {
            userRepository.getCurrentUserId().collect { uid ->
                if (uid > 0) {
                    savedPathRepository.getSavedPaths(uid).collect { list ->
                        _paths.update { list }
                    }
                }
            }
        }
    }

    fun deletePath(pathId: Int) {
        viewModelScope.launch { savedPathRepository.deletePath(pathId) }
    }
}
