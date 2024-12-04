package pl.inpost.recruitmenttask.domain.usecase

import pl.inpost.recruitmenttask.domain.model.Shipment
import pl.inpost.recruitmenttask.domain.repository.ShipmentsRepository

class GetShipmentsGroupedByHighlightedUseCase(
    private val shipmentsRepository: ShipmentsRepository
) {

    suspend fun execute(): GroupedShipmentsResult {
        return try {
            val grouped = shipmentsRepository.getShipments()
                .groupBy { shipment: Shipment -> shipment.operations.highlight }
            val readyToPickup = grouped[true] ?: emptyList()
            val rest = grouped[false] ?: emptyList()

            GroupedShipmentsResult.Success(readyToPickup, rest)
        } catch (e: Exception) {
            // TODO impl GroupedShipmentsResult
            GroupedShipmentsResult.Error
        }
    }
}

sealed class GroupedShipmentsResult {
    data class Success(val readyToPickup: List<Shipment>, val rest: List<Shipment>) : GroupedShipmentsResult()
    object Error : GroupedShipmentsResult() // TODO add error handling and error for this case
}