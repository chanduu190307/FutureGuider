package com.futureguider.data.local.dao

import androidx.room.*
import com.futureguider.data.local.entities.CareerDetailEntity
import com.futureguider.data.local.entities.CertificationEntity
import com.futureguider.data.local.entities.ProjectEntity
import com.futureguider.data.local.entities.SkillEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CareerDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetail(detail: CareerDetailEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSkills(skills: List<SkillEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCertifications(certs: List<CertificationEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProjects(projects: List<ProjectEntity>)

    @Query("SELECT * FROM career_details WHERE nodeId = :nodeId LIMIT 1")
    fun getDetail(nodeId: Int): Flow<CareerDetailEntity?>

    @Query("SELECT * FROM skills WHERE nodeId = :nodeId")
    fun getSkills(nodeId: Int): Flow<List<SkillEntity>>

    @Query("SELECT * FROM certifications WHERE nodeId = :nodeId")
    fun getCertifications(nodeId: Int): Flow<List<CertificationEntity>>

    @Query("SELECT * FROM projects WHERE nodeId = :nodeId")
    fun getProjects(nodeId: Int): Flow<List<ProjectEntity>>

    @Query("SELECT COUNT(*) FROM career_details")
    suspend fun getCount(): Int
}
