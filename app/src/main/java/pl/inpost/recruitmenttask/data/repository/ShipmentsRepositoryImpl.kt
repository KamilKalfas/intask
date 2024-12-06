package pl.inpost.recruitmenttask.data.repository

import pl.inpost.recruitmenttask.data.datasource.ShipmentsLocalDataSource
import pl.inpost.recruitmenttask.data.datasource.ShipmentsRemoteDataSource
import pl.inpost.recruitmenttask.domain.model.Shipment
import pl.inpost.recruitmenttask.domain.repository.ShipmentsRepository

class ShipmentsRepositoryImpl(
    private val remoteDataSource: ShipmentsRemoteDataSource,
    private val localDataSource: ShipmentsLocalDataSource,
) : ShipmentsRepository {

    override suspend fun getShipments(): List<Shipment> {
        val shipments = remoteDataSource.getShipments()
        localDataSource.insertAll(shipments)
        return localDataSource.getShipments()
    }

    override suspend fun archiveShipment(shipmentNumber: String) {
        localDataSource.archiveShipment(shipmentNumber)
    }
}
