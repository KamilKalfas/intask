package pl.inpost.recruitmenttask.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "operations")
data class OperationsEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val manualArchive: Boolean,
    val delete: Boolean,
    val collect: Boolean,
    val highlight: Boolean,
    val expandAvizo: Boolean,
    val endOfWeekCollection: Boolean
)