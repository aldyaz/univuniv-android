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
    @ColumnInfo(COLUMN_STATE_PROVINCE)
    val stateProvince: String,
    @ColumnInfo(COLUMN_ALPHA_TWO_CODE)
    val alphaTwoCode: String,
    val domains: String,
    @ColumnInfo(COLUMN_WEB_PAGES)
    val webPages: String
) {

    companion object {
        const val TABLE_NAME = "university"

        const val COLUMN_STATE_PROVINCE = "state_province"
        const val COLUMN_ALPHA_TWO_CODE = "alpha_two_code"
        const val COLUMN_WEB_PAGES = "web_pages"
    }
}