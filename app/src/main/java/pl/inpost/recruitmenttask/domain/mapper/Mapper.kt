package pl.inpost.recruitmenttask.domain.mapper

import pl.inpost.recruitmenttask.data.model.CustomerDto
import pl.inpost.recruitmenttask.data.model.OperationsDto
import pl.inpost.recruitmenttask.data.model.ShipmentStatusDto
import pl.inpost.recruitmenttask.data.model.ShipmentDto
import pl.inpost.recruitmenttask.domain.model.Customer
import pl.inpost.recruitmenttask.domain.model.Operations
import pl.inpost.recruitmenttask.domain.model.Shipment
import pl.inpost.recruitmenttask.domain.model.ShipmentStatus

fun ShipmentDto.toDomain(): Shipment {
    return Shipment(
        number = number,
        sender = sender.toDomain(),
        status = status.toDomain(this),
        operations = operations.toDomain()
    )
}

fun CustomerDto?.toDomain(): Customer {
    return if (this == null) Customer.EMPTY
    else Customer(
        email = email,
        name = name,
        phoneNumber = phoneNumber
    )
}

fun ShipmentStatusDto.toDomain(shipment: ShipmentDto): ShipmentStatus {
    return when (this) {
        ShipmentStatusDto.ADOPTED_AT_SORTING_CENTER -> ShipmentStatus.AdoptedAtSortingCenter
        ShipmentStatusDto.SENT_FROM_SORTING_CENTER -> ShipmentStatus.SentFromSortingCenter
        ShipmentStatusDto.ADOPTED_AT_SOURCE_BRANCH -> ShipmentStatus.AdoptedAtSourceBranch
        ShipmentStatusDto.SENT_FROM_SOURCE_BRANCH -> ShipmentStatus.SentFromSourceBranch
        ShipmentStatusDto.AVIZO -> ShipmentStatus.Avizo
        ShipmentStatusDto.CONFIRMED -> ShipmentStatus.Confirmed
        ShipmentStatusDto.CREATED -> ShipmentStatus.Created
        ShipmentStatusDto.DELIVERED -> ShipmentStatus.Delivered(requireNotNull(shipment.pickUpDate))
        ShipmentStatusDto.OTHER -> ShipmentStatus.Other
        ShipmentStatusDto.OUT_FOR_DELIVERY -> ShipmentStatus.OutForDelivery
        ShipmentStatusDto.PICKUP_TIME_EXPIRED -> ShipmentStatus.PickupTimeExpired(
            requireNotNull(
                shipment.expiryDate
            )
        )

        ShipmentStatusDto.READY_TO_PICKUP -> ShipmentStatus.ReadyToPickup(requireNotNull(shipment.storedDate))
        ShipmentStatusDto.RETURNED_TO_SENDER -> ShipmentStatus.ReturnedToSender
        ShipmentStatusDto.UNKNOWN -> ShipmentStatus.Unknown
    }
}

fun OperationsDto.toDomain(): Operations {
    return Operations(
        manualArchive = manualArchive,
        delete = delete,
        collect = collect,
        highlight = highlight,
        expandAvizo = expandAvizo,
        endOfWeekCollection = endOfWeekCollection
    )
}
