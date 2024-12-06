package pl.inpost.recruitmenttask.domain.usecase

import pl.inpost.recruitmenttask.domain.exceptions.Failure
import pl.inpost.recruitmenttask.domain.model.Shipment
import pl.inpost.recruitmenttask.domain.repository.ShipmentsRepository

class GetShipmentsGroupedByHighlightedUseCase(
    private val shipmentsRepository: ShipmentsRepository,
    private val sortShipmentsUseCase: SortShipmentsUseCase
) {

    suspend fun execute(): GroupedShipmentsResult {
        return try {
            val grouped = shipmentsRepository.getShipments()
                .filter { it.operations.manualArchive }
                .groupBy { shipment: Shipment -> shipment.operations.highlight }
                .mapValues { (_, group) -> sortShipmentsUseCase.execute(group) }
            val readyToPickup = grouped[true] ?: emptyList()
            val rest = grouped[false] ?: emptyList()
            GroupedShipmentsResult(readyToPickup, rest)
        } catch (e: Exception) {
            throw GroupedShipmentsResultFailure(e)
        }
    }
}

data class GroupedShipmentsResult(val readyToPickup: List<Shipment>, val rest: List<Shipment>)
data class GroupedShipmentsResultFailure(val error: Exception) : Failure.FeatureFailure()