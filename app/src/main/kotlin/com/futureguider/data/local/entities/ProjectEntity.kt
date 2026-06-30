package com.futureguider.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "projects")
data class ProjectEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nodeId: Int,
    val projectName: String,
    val description: String = ""
)
