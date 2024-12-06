package pl.inpost.recruitmenttask.data.repository

import pl.inpost.recruitmenttask.data.datasource.ShipmentsRemoteDataSource
import pl.inpost.recruitmenttask.domain.model.Shipment
import pl.inpost.recruitmenttask.domain.repository.ShipmentsRepository

class ShipmentsRepositoryImpl(
    private val remoteDataSource: ShipmentsRemoteDataSource,
) : ShipmentsRepository {

    override suspend fun getShipments(): List<Shipment> {
       return remoteDataSource.getShipments()
    }
}
