package pl.inpost.recruitmenttask.data.datasource

import pl.inpost.recruitmenttask.data.network.api.ShipmentApi
import pl.inpost.recruitmenttask.domain.mapper.toDomain
import pl.inpost.recruitmenttask.domain.model.Shipment
import pl.inpost.recruitmenttask.domain.repository.GetRemoteShipmentsFailure

class ShipmentsRemoteDataSource(
    private val api: ShipmentApi
) {
    suspend fun getShipments(): List<Shipment> {
        return try {
            api.getShipments().asSequence().map {
                it.toDomain()
            }.toList()
        } catch (e: Exception) {
            throw GetRemoteShipmentsFailure(e)
        }
    }
}