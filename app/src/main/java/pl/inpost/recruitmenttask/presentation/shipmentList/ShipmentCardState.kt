package pl.inpost.recruitmenttask.presentation.shipmentList

import android.content.res.Resources
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import pl.inpost.recruitmenttask.R
import pl.inpost.recruitmenttask.domain.model.Shipment
import pl.inpost.recruitmenttask.domain.model.ShipmentStatus
import pl.inpost.recruitmenttask.domain.model.ShipmentType
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun rememberShipmentCardState(shipment: Shipment): ShipmentCardState {
    val resources = LocalContext.current.resources
    return remember(shipment) {
        ShipmentCardState(shipment = shipment, resources = resources)
    }
}

@Stable
class ShipmentCardState(
    shipment: Shipment,
    resources: Resources,
    locale: Locale = Locale.getDefault()
) {
    val senderName = shipment.sender.let { sender ->
        sequenceOf(sender.name, sender.email, sender.phoneNumber).firstOrNull()
    } ?: resources.getString(R.string.unknown)

    val deliveryIcon = when (shipment.type) {
        ShipmentType.LOCKER -> R.drawable.ic_paczkomat
        ShipmentType.COURIER -> R.drawable.ic_kurier
    }

    @StringRes
    val statusLabel: Int = when (shipment.status) {
        ShipmentStatus.ADOPTED_AT_SORTING_CENTER -> R.string.status_adopted_at_sorting_center
        ShipmentStatus.SENT_FROM_SORTING_CENTER -> R.string.status_sent_from_sorting_center
        ShipmentStatus.ADOPTED_AT_SOURCE_BRANCH -> R.string.status_adopted_at_source_branch
        ShipmentStatus.SENT_FROM_SOURCE_BRANCH -> R.string.status_sent_from_source_branch
        ShipmentStatus.AVIZO -> R.string.status_avizo
        ShipmentStatus.CONFIRMED -> R.string.status_confirmed
        ShipmentStatus.CREATED -> R.string.status_created
        ShipmentStatus.DELIVERED -> R.string.status_delivered
        ShipmentStatus.OTHER -> R.string.status_other
        ShipmentStatus.OUT_FOR_DELIVERY -> R.string.status_out_for_delivery
        ShipmentStatus.PICKUP_TIME_EXPIRED -> R.string.status_pickup_time_expired
        ShipmentStatus.READY_TO_PICKUP -> R.string.status_ready_to_pickup
        ShipmentStatus.RETURNED_TO_SENDER -> R.string.status_returned_to_sender
        ShipmentStatus.UNKNOWN -> R.string.unknown
    }

    val showExtendedStatus = shipment.status in listOf(ShipmentStatus.READY_TO_PICKUP, ShipmentStatus.DELIVERED, ShipmentStatus.PICKUP_TIME_EXPIRED)

    val shipmentFormattedDate = when (shipment.status) {
        ShipmentStatus.READY_TO_PICKUP -> shipmentFormattedDate(requireNotNull(shipment.storedDate), locale)
        ShipmentStatus.DELIVERED -> shipmentFormattedDate(requireNotNull(shipment.pickupDate), locale)
        ShipmentStatus.PICKUP_TIME_EXPIRED -> shipmentFormattedDate(requireNotNull(shipment.expireDate), locale)
        else -> ""
    }

    private fun shipmentFormattedDate(zonedDateTime: ZonedDateTime, locale: Locale): String {
        val dayNameFormatter = DateTimeFormatter.ofPattern("E", locale)
        val dayName = zonedDateTime.format(dayNameFormatter).lowercase(locale)
        val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yy", locale)
        val date = zonedDateTime.format(dateFormatter)
        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm", locale)
        val time = zonedDateTime.format(timeFormatter)

        return "$dayName | $date | $time"
    }
}
