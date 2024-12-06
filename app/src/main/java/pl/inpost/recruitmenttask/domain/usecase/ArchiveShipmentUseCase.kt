package pl.inpost.recruitmenttask.domain.usecase

import pl.inpost.recruitmenttask.domain.repository.ShipmentsRepository

class ArchiveShipmentUseCase(
    private val repository: ShipmentsRepository
) {
    suspend fun execute(shipmentNumber: String) {
        repository.archiveShipment(shipmentNumber)
    }
}