package pl.inpost.recruitmenttask.presentation.shipmentList

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.inpost.recruitmenttask.R
import pl.inpost.recruitmenttask.domain.model.Customer
import pl.inpost.recruitmenttask.domain.model.Operations
import pl.inpost.recruitmenttask.domain.model.Shipment
import pl.inpost.recruitmenttask.domain.model.ShipmentStatus
import pl.inpost.recruitmenttask.domain.model.ShipmentType
import pl.inpost.recruitmenttask.presentation.theme.InPostRecruitmentTaskTheme
import pl.inpost.recruitmenttask.presentation.theme.brandingYellow
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Composable
fun ShipmentCard(
    shipment: Shipment,
    onClick: () -> Unit,
    modifier: Modifier
) {
    val cardState = rememberShipmentCardState(
        shipment = shipment
    )
    ShipmentCardContent(
        number = shipment.number,
        state = cardState,
        onClick = onClick,
        modifier = modifier
    )
}

@Composable
private fun ShipmentCardContent(
    number: String,
    state: ShipmentCardState,
    onClick: () -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colors.background)
            .padding(horizontal = 20.dp)
            .padding(top = 16.dp, bottom = 8.dp)
    ) {
        ShipmentNumberRow(shipmentNumber = number, deliveryIcon = state.deliveryIcon)
        Spacer(modifier = Modifier.height(16.dp))
        ShipmentStatusRow(
            shipmentStatus = stringResource(id = state.statusLabel),
            shipmentFormattedDate = state.shipmentFormattedDate,
            showExtendedStatus = state.showExtendedStatus
        )
        Spacer(modifier = Modifier.height(16.dp))
        ShipmentSenderRow(senderName = state.senderName, onClick = onClick)
    }
}

@Composable
private fun ShipmentNumberRow(
    shipmentNumber: String,
    @DrawableRes deliveryIcon: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = stringResource(id = R.string.shipment_card_number_label))
            Text(text = shipmentNumber)
        }
        Icon(
            painter = painterResource(id = deliveryIcon),
            contentDescription = null,
        )
    }
}

@Composable
private fun ShipmentStatusRow(
    shipmentStatus: String,
    shipmentFormattedDate: String,
    showExtendedStatus: Boolean,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = stringResource(id = R.string.shipment_card_status_label))
            Text(shipmentStatus)
        }
        if (showExtendedStatus) {
            Column {
                Text(text = shipmentStatus)
                Text(text = shipmentFormattedDate)
            }
        }
    }
}

@Composable
private fun ShipmentSenderRow(
    senderName: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp)
            .clickable {
                onClick()
            }
    ) {
        Text(
            modifier = Modifier.align(Alignment.TopStart),
            text = stringResource(id = R.string.shipment_card_sender_label)
        )
        Text(
            modifier = Modifier.align(Alignment.BottomStart),
            text = senderName
        )
        Row(
            modifier = Modifier.align(Alignment.BottomEnd),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                color = Color.Black,
                text = stringResource(id = R.string.shipment_card_more_button_label),
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow),
                tint = MaterialTheme.colors.brandingYellow,
                contentDescription = ""
            )
        }
    }
}

@Preview
@Composable
private fun preview_content() {
    val shipment = Shipment(
        number = "16730345345597442248333",
        type = ShipmentType.LOCKER,
        sender = Customer("sender@example.com", null, null),
        status = ShipmentStatus.READY_TO_PICKUP,
        operations = Operations(false, false, false, false, false, false),
        pickupDate = ZonedDateTime.parse("2022-11-05T04:56:07Z", DateTimeFormatter.ISO_OFFSET_DATE_TIME),
        expireDate = null,
        storedDate = null,
    )
    InPostRecruitmentTaskTheme {
        Surface {
            ShipmentCardContent(
                number = shipment.number,
                state = ShipmentCardState(shipment = shipment, resources = LocalContext.current.resources),
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
