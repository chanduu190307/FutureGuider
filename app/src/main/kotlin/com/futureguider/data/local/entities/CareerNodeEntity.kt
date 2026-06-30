package com.futureguider.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "career_nodes")
data class CareerNodeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val parentId: Int?,
    val type: String, // ROOT, BRANCH, LEAF
    val icon: String = "",
    val colorHex: String = "#6366F1"
)
