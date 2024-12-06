package pl.inpost.recruitmenttask.domain.repository

import pl.inpost.recruitmenttask.domain.exceptions.Failure
import pl.inpost.recruitmenttask.domain.model.Shipment

interface ShipmentsRepository {

    suspend fun getShipments() : List<Shipment>
    suspend fun archiveShipment(shipmentNumber: String)
}

data class MoveToArchiveFailed(val error: Exception) : Failure.FeatureFailure(error)
data class GetRemoteShipmentsFailure(val error: Exception) : Failure.FeatureFailure(error)
data class GetDbShipmentsFailure(val error: Exception) : Failure.FeatureFailure(error)
data class ShipmentsInsertFailure(val error: Exception) : Failure.FeatureFailure(error)
data class OperationUpsertFailure(val error: Exception) : Failure.FeatureFailure(error)
data class CustomerInsertFailure(val error: Exception) : Failure.FeatureFailure(error)