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
        status = status.toDomain(),
        operations = operations.toDomain(),
        storedDate = storedDate,
        expireDate = expiryDate,
        pickupDate = pickUpDate
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

fun ShipmentStatusDto.toDomain(): ShipmentStatus {
    return when (this) {
        ShipmentStatusDto.ADOPTED_AT_SORTING_CENTER -> ShipmentStatus.ADOPTED_AT_SORTING_CENTER
        ShipmentStatusDto.SENT_FROM_SORTING_CENTER -> ShipmentStatus.SENT_FROM_SORTING_CENTER
        ShipmentStatusDto.ADOPTED_AT_SOURCE_BRANCH -> ShipmentStatus.ADOPTED_AT_SOURCE_BRANCH
        ShipmentStatusDto.SENT_FROM_SOURCE_BRANCH -> ShipmentStatus.SENT_FROM_SOURCE_BRANCH
        ShipmentStatusDto.AVIZO -> ShipmentStatus.AVIZO
        ShipmentStatusDto.CONFIRMED -> ShipmentStatus.CONFIRMED
        ShipmentStatusDto.CREATED -> ShipmentStatus.CREATED
        ShipmentStatusDto.DELIVERED -> ShipmentStatus.DELIVERED
        ShipmentStatusDto.OTHER -> ShipmentStatus.OTHER
        ShipmentStatusDto.OUT_FOR_DELIVERY -> ShipmentStatus.OUT_FOR_DELIVERY
        ShipmentStatusDto.PICKUP_TIME_EXPIRED -> ShipmentStatus.PICKUP_TIME_EXPIRED
        ShipmentStatusDto.READY_TO_PICKUP -> ShipmentStatus.READY_TO_PICKUP
        ShipmentStatusDto.RETURNED_TO_SENDER -> ShipmentStatus.RETURNED_TO_SENDER
        ShipmentStatusDto.UNKNOWN -> ShipmentStatus.UNKNOWN
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
