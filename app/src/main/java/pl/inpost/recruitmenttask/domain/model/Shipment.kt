package pl.inpost.recruitmenttask.domain.model

import java.time.ZonedDateTime
data class Shipment(
    val number: String,
    val sender: Customer,
    val status: ShipmentStatus,
    val operations: Operations,
    val pickupDate: ZonedDateTime?,
    val expireDate: ZonedDateTime?,
    val storedDate: ZonedDateTime?,
)
