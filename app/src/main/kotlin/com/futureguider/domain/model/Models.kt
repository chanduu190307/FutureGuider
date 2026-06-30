package com.futureguider.domain.model

data class User(
    val id: Int = 0,
    val name: String,
    val email: String,
    val passwordHash: String,
    val createdAt: Long = System.currentTimeMillis()
)

data class CareerNode(
    val id: Int,
    val name: String,
    val parentId: Int?,
    val type: NodeType,
    val colorHex: String = "#6366F1",
    val hasChildren: Boolean = false
)

enum class NodeType { ROOT, BRANCH, LEAF }

data class CareerDetail(
    val nodeId: Int,
    val description: String,
    val suggestedNextStep: String,
    val skills: List<String> = emptyList(),
    val certifications: List<Certification> = emptyList(),
    val projects: List<Project> = emptyList()
)

data class Certification(
    val id: Int = 0,
    val name: String,
    val provider: String = ""
)

data class Project(
    val id: Int = 0,
    val name: String,
    val description: String = ""
)

data class SavedPath(
    val id: Int = 0,
    val userId: Int,
    val leafNodeId: Int,
    val pathSteps: List<String>,
    val savedAt: Long = System.currentTimeMillis()
)
