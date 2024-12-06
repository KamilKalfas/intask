package pl.inpost.recruitmenttask.data.datasource

import pl.inpost.recruitmenttask.data.database.CustomerDao
import pl.inpost.recruitmenttask.data.database.ShipmentsDao
import pl.inpost.recruitmenttask.data.database.entity.OperationsDao
import pl.inpost.recruitmenttask.domain.model.Shipment

class ShipmentsLocalDataSource(
    private val shipmentsDao: ShipmentsDao,
    private val operationsDao: OperationsDao,
    private val customersDao: CustomerDao
) {
    suspend fun insertAll(shipments: List<Shipment>) {

    }
}
