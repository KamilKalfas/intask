package pl.inpost.recruitmenttask.domain.model

import java.time.ZonedDateTime

sealed class ShipmentStatus {
    object AdoptedAtSortingCenter : ShipmentStatus()
    object SentFromSortingCenter : ShipmentStatus()
    object AdoptedAtSourceBranch : ShipmentStatus()
    object SentFromSourceBranch : ShipmentStatus()
    object Avizo : ShipmentStatus()
    object Confirmed : ShipmentStatus()
    object Created : ShipmentStatus()
    data class Delivered(val date: ZonedDateTime) : ShipmentStatus()
    object Other : ShipmentStatus()
    object OutForDelivery : ShipmentStatus()
    data class PickupTimeExpired(val date: ZonedDateTime) : ShipmentStatus()
    data class ReadyToPickup(val date: ZonedDateTime) : ShipmentStatus()
    object ReturnedToSender : ShipmentStatus()
    object Unknown : ShipmentStatus()
}
