package pl.inpost.recruitmenttask.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import pl.inpost.recruitmenttask.domain.model.ShipmentStatus
import pl.inpost.recruitmenttask.domain.model.ShipmentType
import java.time.ZonedDateTime

@Entity(tableName = "shipments")
data class ShipmentEntity(
    @PrimaryKey @ColumnInfo(name = "number") val number: String,
    @ColumnInfo(name = "type") val shipmentType: ShipmentType,
    @ColumnInfo(name = "status") val status: ShipmentStatus,
    @ColumnInfo(name = "expire_date") val expireDate: ZonedDateTime?,
    @ColumnInfo(name = "stored_date") val storedDate: ZonedDateTime?,
    @ColumnInfo(name = "pickup_date") val pickupDate: ZonedDateTime?,
    val senderId: Long?,
    val operationsId: Long
)