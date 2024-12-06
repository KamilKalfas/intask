package pl.inpost.recruitmenttask.data.database

import androidx.room.TypeConverter
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): ZonedDateTime? {
        return value?.let {
            val zoneId = ZoneId.systemDefault()
            val instant = Instant.ofEpochMilli(it)
            ZonedDateTime.ofInstant(instant, zoneId)
        }
    }

    @TypeConverter
    fun dateToTimestamp(date: ZonedDateTime?): Long? {
        return date?.toEpochSecond()
    }
}