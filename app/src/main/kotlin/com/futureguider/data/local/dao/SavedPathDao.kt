package com.futureguider.data.local.dao

import androidx.room.*
import com.futureguider.data.local.entities.SavedPathEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedPathDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPath(path: SavedPathEntity): Long

    @Query("SELECT * FROM saved_paths WHERE userId = :userId ORDER BY savedAt DESC")
    fun getPathsForUser(userId: Int): Flow<List<SavedPathEntity>>

    @Delete
    suspend fun deletePath(path: SavedPathEntity)

    @Query("DELETE FROM saved_paths WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("SELECT COUNT(*) FROM saved_paths WHERE userId = :userId AND leafNodeId = :leafNodeId")
    suspend fun isAlreadySaved(userId: Int, leafNodeId: Int): Int
}
