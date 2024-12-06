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
        val shipments = if (!dataSavedInDb) {
            val remoteShipments = remoteDataSource.getShipments()
            if (remoteShipments.isNotEmpty()) {
                localDataSource.insertAll(remoteShipments)
                dataSavedInDb = true
            }
            remoteShipments
        } else localDataSource.getShipments()
        return shipments
    }
}}
