package com.aldyaz.univuniv.source.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = UniversityDbModel.TABLE_NAME)
data class UniversityDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val country: String,
    val domains: List<String>,
    @ColumnInfo(COLUMN_WEB_PAGES)
    val webPages: List<String>
) {

    companion object {
        const val TABLE_NAME = "university"

        const val COLUMN_WEB_PAGES = "web_pages"
    }
}