package com.futureguider.data.repository

import com.futureguider.data.local.dao.CareerDetailDao
import com.futureguider.data.local.dao.CareerNodeDao
import com.futureguider.data.local.toDomain
import com.futureguider.domain.model.CareerDetail
import com.futureguider.domain.model.CareerNode
import com.futureguider.domain.usecase.CareerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CareerRepositoryImpl @Inject constructor(
    private val nodeDao: CareerNodeDao,
    private val detailDao: CareerDetailDao
) : CareerRepository {

    override fun getRootNodes(): Flow<List<CareerNode>> =
        nodeDao.getRootNodes().map { list ->
            list.map { entity ->
                val children = nodeDao.getChildrenOnce(entity.id)
                entity.toDomain(hasChildren = children.isNotEmpty())
            }
        }

    override fun getChildNodes(parentId: Int): Flow<List<CareerNode>> =
        nodeDao.getChildNodes(parentId).map { list ->
            list.map { entity ->
                val children = nodeDao.getChildrenOnce(entity.id)
                entity.toDomain(hasChildren = children.isNotEmpty())
            }
        }

    override fun getCareerDetail(nodeId: Int): Flow<CareerDetail?> {
        val detailFlow = detailDao.getDetail(nodeId)
        val skillsFlow = detailDao.getSkills(nodeId)
        val certsFlow  = detailDao.getCertifications(nodeId)
        val projsFlow  = detailDao.getProjects(nodeId)

        return combine(detailFlow, skillsFlow, certsFlow, projsFlow) { detail, skills, certs, projs ->
            detail?.toDomain(skills, certs, projs)
        }
    }

    override suspend fun buildPathToNode(nodeId: Int): List<String> {
        val path = mutableListOf<String>()
        var current = nodeDao.getNodeById(nodeId)
        while (current != null) {
            path.add(0, current.name)
            current = current.parentId?.let { nodeDao.getNodeById(it) }
        }
        return path
    }
}
