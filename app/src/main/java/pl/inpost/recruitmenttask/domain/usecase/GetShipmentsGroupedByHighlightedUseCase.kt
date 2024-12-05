package pl.inpost.recruitmenttask.domain.usecase

import pl.inpost.recruitmenttask.domain.model.Shipment
import pl.inpost.recruitmenttask.domain.repository.ShipmentsRepository

class GetShipmentsGroupedByHighlightedUseCase(
    private val shipmentsRepository: ShipmentsRepository,
    private val sortShipmentsUseCase: SortShipmentsUseCase
) {

    suspend fun execute(): GroupedShipmentsResult {
        return try {
            val grouped = shipmentsRepository.getShipments()
                .groupBy { shipment: Shipment -> shipment.operations.highlight }
                .mapValues { (_, group) -> sortShipmentsUseCase.execute(group) }
            val readyToPickup = grouped[true] ?: emptyList()
            val rest = grouped[false] ?: emptyList()

            GroupedShipmentsResult.Success(readyToPickup, rest)
        } catch (e: Exception) {
            GroupedShipmentsResult.Error(e)
        }
    }
}

sealed class GroupedShipmentsResult {
    data class Success(val readyToPickup: List<Shipment>, val rest: List<Shipment>) : GroupedShipmentsResult()
    data class Error(val exception: Exception) : GroupedShipmentsResult()
}
