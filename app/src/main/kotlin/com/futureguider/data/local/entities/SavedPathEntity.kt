package com.futureguider.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_paths")
data class SavedPathEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int,
    val leafNodeId: Int,
    val pathJson: String, // JSON array of node names from root to leaf
    val savedAt: Long = System.currentTimeMillis()
)
