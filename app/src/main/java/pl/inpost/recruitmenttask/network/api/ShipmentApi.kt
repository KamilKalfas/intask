package pl.inpost.recruitmenttask.network.api

import pl.inpost.recruitmenttask.data.model.ShipmentDto

interface ShipmentApi {
    suspend fun getShipments(): List<ShipmentDto>
}
