package com.aldyaz.univuniv.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aldyaz.univuniv.source.local.model.UniversityDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface UniversityDao {

    @Query("SELECT * FROM university")
    fun getUniversities(): Flow<List<UniversityDbModel>>

    @Query("SELECT * FROM university WHERE name LIKE '%' || :query || '%'")
    fun getUniversities(
        query: String
    ): Flow<List<UniversityDbModel>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun saveUniversities(items: List<UniversityDbModel>)

    @Query("SELECT (SELECT COUNT(*) FROM university) == 0")
    fun isTableEmpty(): Boolean

}