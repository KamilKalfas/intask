package pl.inpost.recruitmenttask.domain.usecase

import pl.inpost.recruitmenttask.domain.model.Shipment

class SortShipmentsUseCase {

    /**
     * Sorts shipment in following order
     * - status
     * - pickupDate
     * - expireDate
     * - storedDate
     * - number
     *
     * Converting to epoch seconds for null values to be at the end
     */
    fun execute(shipments: List<Shipment>): List<Shipment> {
        return shipments.asSequence()
            .sortedWith(compareBy<Shipment> { it.status.ordinal }
            .thenBy { it.pickupDate?.toEpochSecond() ?: Long.MAX_VALUE }
            .thenBy { it.expireDate?.toEpochSecond() ?: Long.MAX_VALUE }
            .thenBy { it.storedDate?.toEpochSecond() ?: Long.MAX_VALUE }
            .thenBy { it.number }
        ).toList()
    }
}
