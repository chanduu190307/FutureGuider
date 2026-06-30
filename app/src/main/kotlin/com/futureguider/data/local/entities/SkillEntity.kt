package com.futureguider.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "skills")
data class SkillEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nodeId: Int,
    val skillName: String
)
