package com.futureguider.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "certifications")
data class CertificationEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nodeId: Int,
    val certName: String,
    val provider: String = ""
)
