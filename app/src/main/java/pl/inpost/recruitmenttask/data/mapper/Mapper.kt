package pl.inpost.recruitmenttask.data.mapper

import pl.inpost.recruitmenttask.data.database.entity.CustomerEntity
import pl.inpost.recruitmenttask.data.database.entity.OperationsEntity
import pl.inpost.recruitmenttask.data.database.entity.ShipmentEntity
import pl.inpost.recruitmenttask.domain.model.Customer
import pl.inpost.recruitmenttask.domain.model.Operations
import pl.inpost.recruitmenttask.domain.model.Shipment

fun Customer.toEntity() = CustomerEntity(
    name = name,
    email = email,
    phone = phoneNumber
)

fun CustomerEntity?.toDomain(): Customer {
    return if (this == null) Customer.EMPTY
    else Customer(
        email = email,
        name = name,
        phoneNumber = phone
    )
}

fun Operations.toEntity() = OperationsEntity(
    endOfWeekCollection = endOfWeekCollection,
    highlight = highlight,
    manualArchive = manualArchive,
    expandAvizo = expandAvizo,
    delete = delete,
    collect = collect
)

fun OperationsEntity.toDomain() = Operations(
    manualArchive = manualArchive,
    delete = delete,
    collect = collect,
    highlight = highlight,
    expandAvizo = expandAvizo,
    endOfWeekCollection = endOfWeekCollection
)

fun Shipment.toEntity(senderId: Long?, operationsId: Long) = ShipmentEntity(
    number = number,
    shipmentType = type,
    status = status,
    expireDate = expireDate,
    storedDate = storedDate,
    pickupDate = pickupDate,
    senderId = senderId,
    operationsId = operationsId
)