package pl.inpost.recruitmenttask.mockk

import pl.inpost.recruitmenttask.domain.model.Customer
import pl.inpost.recruitmenttask.domain.model.Operations
import pl.inpost.recruitmenttask.domain.model.Shipment
import pl.inpost.recruitmenttask.domain.model.ShipmentStatus
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
    sender = Customer(null, null, null)
)
