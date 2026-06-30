package com.futureguider.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "career_details")
data class CareerDetailEntity(
    @PrimaryKey val nodeId: Int,
    val description: String,
    val suggestedNextStep: String,
    val averageSalary: String = "",
    val jobOutlook: String = ""
)
