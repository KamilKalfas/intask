package pl.inpost.recruitmenttask.domain.model

data class Shipment(
    val number: String,
    val sender: Customer,
    val status: ShipmentStatus
)
