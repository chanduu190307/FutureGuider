package com.futureguider.data.local.dao

import androidx.room.*
import com.futureguider.data.local.entities.CareerNodeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CareerNodeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(nodes: List<CareerNodeEntity>)

    @Query("SELECT * FROM career_nodes WHERE parentId IS NULL ORDER BY id ASC")
    fun getRootNodes(): Flow<List<CareerNodeEntity>>

    @Query("SELECT * FROM career_nodes WHERE parentId = :parentId ORDER BY id ASC")
    fun getChildNodes(parentId: Int): Flow<List<CareerNodeEntity>>

    @Query("SELECT * FROM career_nodes WHERE id = :id LIMIT 1")
    suspend fun getNodeById(id: Int): CareerNodeEntity?

    @Query("SELECT * FROM career_nodes WHERE name = :name LIMIT 1")
    suspend fun getNodeByName(name: String): CareerNodeEntity?

    @Query("SELECT COUNT(*) FROM career_nodes")
    suspend fun getCount(): Int

    @Query("SELECT * FROM career_nodes WHERE parentId = :parentId")
    suspend fun getChildrenOnce(parentId: Int): List<CareerNodeEntity>

    @Query("SELECT * FROM career_nodes WHERE id = :id")
    fun getNodeFlow(id: Int): Flow<CareerNodeEntity?>
}
