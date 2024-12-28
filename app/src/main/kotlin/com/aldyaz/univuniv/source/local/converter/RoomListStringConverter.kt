package com.aldyaz.univuniv.source.local.converter

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class RoomListStringConverter {

    @TypeConverter
    fun listToJsonString(items: List<String>): String {
        return Json.encodeToString(items)
    }

    @TypeConverter
    fun jsonStringToList(json: String): List<String> {
        return Json.decodeFromString(json)
    }
}