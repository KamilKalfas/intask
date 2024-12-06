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
        // if we would have actual network call we could use last-modified header
        // to check if there are changes and save them
        // since there is no synchronisation client <-> server
        // we call fake API only if we haven't saved anything to local db
        // to persist manual archive changes
        return if (localDataSource.hasNoSavedData()) {
            val shipments = remoteDataSource.getShipments()
            localDataSource.insertAll(shipments)
            shipments
        } else localDataSource.getShipments()
    }
}