package com.futureguider.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.futureguider.domain.model.CareerNode
import com.futureguider.domain.usecase.CareerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CareerTreeUiState(
    val rootNodes: List<CareerNode> = emptyList(),
    val expandedNodeIds: Set<Int> = emptySet(),
    val childrenMap: Map<Int, List<CareerNode>> = emptyMap(),
    val selectedRootId: Int? = null,
    val isLoading: Boolean = true
)

@HiltViewModel
class CareerTreeViewModel @Inject constructor(
    private val careerRepository: CareerRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CareerTreeUiState())
    val uiState: StateFlow<CareerTreeUiState> = _uiState.asStateFlow()

    // Holds active collection jobs so we can cancel when node collapsed
    private val childJobs = mutableMapOf<Int, kotlinx.coroutines.Job>()

    init {
        observeRootNodes()
    }

    private fun observeRootNodes() {
        viewModelScope.launch {
            careerRepository.getRootNodes().collect { nodes ->
                _uiState.update { it.copy(rootNodes = nodes, isLoading = false) }
            }
        }
    }

    fun setRootFilter(nodeId: Int?) {
        _uiState.update { it.copy(selectedRootId = nodeId, expandedNodeIds = emptySet(), childrenMap = emptyMap()) }
        childJobs.values.forEach { it.cancel() }
        childJobs.clear()
    }

    fun toggleNode(node: CareerNode) {
        val expanded = _uiState.value.expandedNodeIds
        if (node.id in expanded) {
            // Collapse: remove from expanded and remove children recursively
            val toCollapse = getAllDescendantIds(node.id) + node.id
            val newExpanded = expanded - toCollapse
            val newChildren = _uiState.value.childrenMap.toMutableMap()
            toCollapse.forEach {
                newChildren.remove(it)
                childJobs[it]?.cancel()
                childJobs.remove(it)
            }
            _uiState.update { it.copy(expandedNodeIds = newExpanded, childrenMap = newChildren) }
        } else {
            // Expand: add to expanded and start collecting children
            _uiState.update { it.copy(expandedNodeIds = expanded + node.id) }
            if (node.hasChildren) {
                val job = viewModelScope.launch {
                    careerRepository.getChildNodes(node.id).collect { children ->
                        _uiState.update { state ->
                            state.copy(childrenMap = state.childrenMap + (node.id to children))
                        }
                    }
                }
                childJobs[node.id] = job
            }
        }
    }

    private fun getAllDescendantIds(nodeId: Int): Set<Int> {
        val result = mutableSetOf<Int>()
        val children = _uiState.value.childrenMap[nodeId] ?: return result
        for (child in children) {
            result.add(child.id)
            result.addAll(getAllDescendantIds(child.id))
        }
        return result
    }

    fun isExpanded(nodeId: Int) = nodeId in _uiState.value.expandedNodeIds

    fun getChildren(nodeId: Int): List<CareerNode> =
        _uiState.value.childrenMap[nodeId] ?: emptyList()
}
