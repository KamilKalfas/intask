package pl.inpost.recruitmenttask.domain.repository

import pl.inpost.recruitmenttask.domain.model.Shipment

interface ShipmentsRepository {

    suspend fun getShipments() : List<Shipment>
}
