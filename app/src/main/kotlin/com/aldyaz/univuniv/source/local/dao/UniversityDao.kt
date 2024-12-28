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

    @Query("SELECT * FROM university WHERE name=:name")
    fun getUniversitiesByName(
        name: String
    ): Flow<List<UniversityDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUniversities(vararg item: UniversityDbModel)

}