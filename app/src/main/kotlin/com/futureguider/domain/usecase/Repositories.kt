package com.futureguider.domain.usecase

import com.futureguider.domain.model.*
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun register(name: String, email: String, password: String): Result<User>
    suspend fun login(email: String, password: String): Result<User>
    suspend fun saveSession(user: User)
    suspend fun logout()
    fun isLoggedIn(): Flow<Boolean>
    fun getCurrentUserId(): Flow<Int>
    fun getUserName(): Flow<String>
}

interface CareerRepository {
    fun getRootNodes(): Flow<List<CareerNode>>
    fun getChildNodes(parentId: Int): Flow<List<CareerNode>>
    fun getCareerDetail(nodeId: Int): Flow<CareerDetail?>
    suspend fun buildPathToNode(nodeId: Int): List<String>
}

interface SavedPathRepository {
    fun getSavedPaths(userId: Int): Flow<List<SavedPath>>
    suspend fun savePath(userId: Int, leafNodeId: Int): Result<Unit>
    suspend fun deletePath(pathId: Int)
    suspend fun isAlreadySaved(userId: Int, leafNodeId: Int): Boolean
}
