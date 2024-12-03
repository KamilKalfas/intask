package pl.inpost.recruitmenttask.data.network.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import dagger.Reusable
import pl.inpost.recruitmenttask.data.model.ShipmentStatusDto
import javax.inject.Inject

@Reusable
class ShipmentStatusTypeAdapter @Inject constructor() {

    @FromJson
    fun toShipmentStatus(value: String): ShipmentStatusDto {
        return try {
            ShipmentStatusDto.valueOf(value)
        } catch (e: IllegalArgumentException) {
            ShipmentStatusDto.UNKNOWN
        }
    }

    @ToJson
    fun fromShipmentStatus(status: ShipmentStatusDto): String = status.name
}
