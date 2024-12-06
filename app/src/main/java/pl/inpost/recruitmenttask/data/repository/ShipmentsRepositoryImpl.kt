package pl.inpost.recruitmenttask.data.repository

import pl.inpost.recruitmenttask.data.datasource.ShipmentsLocalDataSource
import pl.inpost.recruitmenttask.data.datasource.ShipmentsRemoteDataSource
import pl.inpost.recruitmenttask.domain.model.Shipment
import pl.inpost.recruitmenttask.domain.repository.ShipmentsRepository

class ShipmentsRepositoryImpl(
    private val remoteDataSource: ShipmentsRemoteDataSource,
    private val localDataSource: ShipmentsLocalDataSource,
) : ShipmentsRepository {

    private var dataSavedInDb = false

    override suspend fun getShipments(): List<Shipment> {
        val shipments = remoteDataSource.getShipments()
        if (shipments.isNotEmpty() && !dataSavedInDb) {
            dataSavedInDb = true
            localDataSource.insertAll(shipments)
        }
        return shipments
    }
}
