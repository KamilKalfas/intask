package pl.inpost.recruitmenttask.data.database

import androidx.room.TypeConverter
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class Converters {
    private val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME

    @TypeConverter
    fun fromString(value: String?): ZonedDateTime? {
        return try {
            formatter.parse(value, ZonedDateTime::from)
        } catch (e: NullPointerException) {
            null
        }
    }

    @TypeConverter
    fun dateToString(date: ZonedDateTime?): String? = date?.format(formatter)
}
