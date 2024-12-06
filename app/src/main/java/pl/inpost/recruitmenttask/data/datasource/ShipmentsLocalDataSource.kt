package pl.inpost.recruitmenttask.data.datasource

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pl.inpost.recruitmenttask.data.database.CustomersDao
import pl.inpost.recruitmenttask.data.database.OperationsDao
import pl.inpost.recruitmenttask.data.database.ShipmentsDao
import pl.inpost.recruitmenttask.data.database.entity.ShipmentEntity
import pl.inpost.recruitmenttask.data.mapper.toDomain
import pl.inpost.recruitmenttask.data.mapper.toEntity
import pl.inpost.recruitmenttask.domain.model.Shipment
import pl.inpost.recruitmenttask.domain.repository.CustomerInsertFailure
import pl.inpost.recruitmenttask.domain.repository.GetDbShipmentsFailure
import pl.inpost.recruitmenttask.domain.repository.MoveToArchiveFailed
import pl.inpost.recruitmenttask.domain.repository.OperationUpsertFailure
import pl.inpost.recruitmenttask.domain.repository.ShipmentsInsertFailure

class ShipmentsLocalDataSource(
    private val shipmentsDao: ShipmentsDao,
    private val operationsDao: OperationsDao,
    private val customersDao: CustomersDao
) {
    suspend fun insertAll(shipments: List<Shipment>) {
        withContext(Dispatchers.IO) {
            val entities = mutableListOf<ShipmentEntity>()
            shipments.forEach {
                val operationId = try {
                    operationsDao.upsert(it.operations.toEntity())
                } catch (e: Exception) {
                    throw OperationUpsertFailure(e)
                }
                val senderId = try {
                    customersDao.insert(it.sender.toEntity())
                } catch (e: Exception) {
                    throw CustomerInsertFailure(e)
                }
                entities.add(it.toEntity(senderId = senderId, operationsId = operationId))
            }
            try {
                shipmentsDao.insertAll(entities)
            } catch (e: Exception) {
                throw ShipmentsInsertFailure(e)
            }
        }
    }

    suspend fun getShipments(): List<Shipment> {
        return try {
            shipmentsDao.getAll().map { it.toDomain() }
        } catch (e: Exception) {
            throw GetDbShipmentsFailure(e)
        }
    }

    suspend fun hasNoSavedData(): Boolean = !shipmentsDao.hasRows()

    suspend fun archiveShipment(shipmentNumber: String) {
        try {
            val operationId = shipmentsDao.getOperationIdByShipmentNumber(shipmentNumber)
            if (operationId != null) operationsDao.updateArchiveFlag(operationId, archive = true)
        } catch (e: Exception) {
            throw MoveToArchiveFailed(e)
        }
    }
}
