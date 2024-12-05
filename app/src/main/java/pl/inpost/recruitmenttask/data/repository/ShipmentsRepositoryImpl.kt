package pl.inpost.recruitmenttask.data.repository

import pl.inpost.recruitmenttask.data.network.api.ShipmentApi
import pl.inpost.recruitmenttask.domain.mapper.toDomain
import pl.inpost.recruitmenttask.domain.model.Shipment
import pl.inpost.recruitmenttask.domain.repository.ShipmentsRepository

class ShipmentsRepositoryImpl(
    private val shipmentApi: ShipmentApi
) : ShipmentsRepository {

    override suspend fun getShipments(): List<Shipment> {
        return shipmentApi.getShipments().asSequence().map {
            it.toDomain()
        }.toList()
    }
}
