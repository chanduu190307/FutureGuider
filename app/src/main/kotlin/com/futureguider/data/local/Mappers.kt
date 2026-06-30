package com.futureguider.data.local

import com.futureguider.data.local.entities.*
import com.futureguider.domain.model.*

fun UserEntity.toDomain() = User(id, name, email, passwordHash, createdAt)
fun User.toEntity()       = UserEntity(id, name, email, passwordHash, createdAt)

fun CareerNodeEntity.toDomain(hasChildren: Boolean = false) = CareerNode(
    id = id,
    name = name,
    parentId = parentId,
    type = when (type) {
        "ROOT"   -> NodeType.ROOT
        "BRANCH" -> NodeType.BRANCH
        else     -> NodeType.LEAF
    },
    colorHex = colorHex,
    hasChildren = hasChildren
)

fun CareerDetailEntity.toDomain(
    skills: List<SkillEntity>,
    certs: List<CertificationEntity>,
    projects: List<ProjectEntity>
) = CareerDetail(
    nodeId = nodeId,
    description = description,
    suggestedNextStep = suggestedNextStep,
    skills = skills.map { it.skillName },
    certifications = certs.map { Certification(it.id, it.certName, it.provider) },
    projects = projects.map { Project(it.id, it.projectName, it.description) }
)

fun SavedPathEntity.toDomain(): SavedPath {
    val steps = pathJson
        .trim('[', ']')
        .split(",")
        .map { it.trim().trim('"') }
        .filter { it.isNotBlank() }
    return SavedPath(id, userId, leafNodeId, steps, savedAt)
}

fun SavedPath.toEntity(): SavedPathEntity {
    val json = "[${pathSteps.joinToString(",") { "\"$it\"" }}]"
    return SavedPathEntity(id, userId, leafNodeId, json, savedAt)
}
