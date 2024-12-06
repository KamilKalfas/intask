package pl.inpost.recruitmenttask.data.datasource

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pl.inpost.recruitmenttask.data.database.CustomerDao
import pl.inpost.recruitmenttask.data.database.ShipmentsDao
import pl.inpost.recruitmenttask.data.database.entity.OperationsDao
import pl.inpost.recruitmenttask.data.database.entity.ShipmentEntity
import pl.inpost.recruitmenttask.data.mapper.toEntity
import pl.inpost.recruitmenttask.domain.model.Shipment

class ShipmentsLocalDataSource(
    private val shipmentsDao: ShipmentsDao,
    private val operationsDao: OperationsDao,
    private val customersDao: CustomerDao
) {
    suspend fun insertAll(shipments: List<Shipment>) {
        withContext(Dispatchers.IO) {
            val entities = mutableListOf<ShipmentEntity>()
            shipments.forEach {
                val operationId = operationsDao.insert(it.operations.toEntity())
                val senderId = customersDao.insert(it.sender.toEntity())
                entities.add(it.toEntity(senderId = senderId, operationsId = operationId))
            }
            shipmentsDao.insertAll(entities)
        }
    }
}
