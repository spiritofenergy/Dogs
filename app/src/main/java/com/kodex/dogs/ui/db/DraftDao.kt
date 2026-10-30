package com.kodex.dogs.ui.db


import androidx.room.*
 import com.kodex.dogs.data.source.local.DraftEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DraftDao {
    @Query("SELECT * FROM drafts ORDER BY createdAt DESC LIMIT 1")
    fun getLatestDraft(): Flow<DraftEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveDraft(draft: DraftEntity): Long

    @Query("DELETE FROM drafts WHERE id = :id")
    suspend fun deleteDraft(id: Long)

    @Query("DELETE FROM drafts")
    suspend fun clearAllDrafts()
}