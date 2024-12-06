package pl.inpost.recruitmenttask.mockk

import pl.inpost.recruitmenttask.data.model.CustomerDto
import pl.inpost.recruitmenttask.data.model.OperationsDto
import pl.inpost.recruitmenttask.data.model.ShipmentDto
import pl.inpost.recruitmenttask.data.model.ShipmentStatusDto
import pl.inpost.recruitmenttask.data.model.ShipmentTypeDto
import pl.inpost.recruitmenttask.domain.model.Customer
import pl.inpost.recruitmenttask.domain.model.Operations
import pl.inpost.recruitmenttask.domain.model.Shipment
import pl.inpost.recruitmenttask.domain.model.ShipmentStatus
import pl.inpost.recruitmenttask.domain.model.ShipmentType
import java.time.ZonedDateTime

fun fakeShipment(
    number: String,
    status: ShipmentStatus,
    pickupDate: ZonedDateTime? = null,
    expiryDate: ZonedDateTime? = null,
    storedDate: ZonedDateTime? = null,
    highlight: Boolean = false
) = Shipment(
    number = number,
    status = status,
    pickupDate = pickupDate,
    expireDate = expiryDate,
    storedDate = storedDate,
    operations = Operations(
        manualArchive = false,
        delete = false,
        collect = false,
        highlight = highlight,
        expandAvizo = false,
        endOfWeekCollection = false,
    ),
    sender = Customer(null, null, null),
    type = ShipmentType.LOCKER
)

fun fakeShipmentDto(
    number: String,
    senderName: String,
    operationHighlight: Boolean
) = ShipmentDto(
    number = number,
    shipmentType = ShipmentTypeDto.PARCEL_LOCKER,
    status = ShipmentStatusDto.DELIVERED,
    eventLog = emptyList(),
    openCode = null,
    expiryDate = null,
    storedDate = null,
    pickUpDate = ZonedDateTime.now(),
    receiver = CustomerDto(
        email = null,
        phoneNumber = null,
        name = null
    ),
    sender = CustomerDto(
        email = null,
        phoneNumber = null,
        name = senderName
    ),
    operations = OperationsDto(
        manualArchive = false,
        delete = false,
        collect = false,
        highlight = operationHighlight,
        expandAvizo = false,
        endOfWeekCollection = false
    )
)
