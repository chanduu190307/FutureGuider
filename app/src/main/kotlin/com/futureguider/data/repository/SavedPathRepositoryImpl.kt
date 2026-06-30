package com.futureguider.data.repository

import com.futureguider.data.local.dao.CareerNodeDao
import com.futureguider.data.local.dao.SavedPathDao
import com.futureguider.data.local.toDomain
import com.futureguider.data.local.toEntity
import com.futureguider.domain.model.SavedPath
import com.futureguider.domain.usecase.SavedPathRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SavedPathRepositoryImpl @Inject constructor(
    private val savedPathDao: SavedPathDao,
    private val nodeDao: CareerNodeDao
) : SavedPathRepository {

    override fun getSavedPaths(userId: Int): Flow<List<SavedPath>> =
        savedPathDao.getPathsForUser(userId).map { list -> list.map { it.toDomain() } }

    override suspend fun savePath(userId: Int, leafNodeId: Int): Result<Unit> {
        return try {
            val pathSteps = buildPath(leafNodeId)
            val entity = SavedPath(
                userId = userId,
                leafNodeId = leafNodeId,
                pathSteps = pathSteps
            ).toEntity()
            savedPathDao.insertPath(entity)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deletePath(pathId: Int) = savedPathDao.deleteById(pathId)

    override suspend fun isAlreadySaved(userId: Int, leafNodeId: Int): Boolean =
        savedPathDao.isAlreadySaved(userId, leafNodeId) > 0

    private suspend fun buildPath(nodeId: Int): List<String> {
        val path = mutableListOf<String>()
        var current = nodeDao.getNodeById(nodeId)
        while (current != null) {
            path.add(0, current.name)
            current = current.parentId?.let { nodeDao.getNodeById(it) }
        }
        return path
    }
}
