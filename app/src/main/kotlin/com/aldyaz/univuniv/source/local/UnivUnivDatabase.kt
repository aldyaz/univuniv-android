package com.aldyaz.univuniv.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aldyaz.univuniv.source.local.dao.UniversityDao
import com.aldyaz.univuniv.source.local.model.UniversityDbModel

@Database(
    version = UnivUnivDatabase.DATABASE_VERSION,
    entities = [
        UniversityDbModel::class
    ],
    exportSchema = false
)
abstract class UnivUnivDatabase : RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "UnivUnivDatabase"
        const val DATABASE_VERSION = 1

        fun instance(
            context: Context
        ): UnivUnivDatabase = Room.databaseBuilder(
            context = context,
            klass = UnivUnivDatabase::class.java,
            name = DATABASE_NAME
        )
            .allowMainThreadQueries()
            .build()
    }

    abstract fun universityDao(): UniversityDao
}