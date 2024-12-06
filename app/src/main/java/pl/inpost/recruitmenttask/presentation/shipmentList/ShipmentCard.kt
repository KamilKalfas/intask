package pl.inpost.recruitmenttask.presentation.shipmentList

import androidx.annotation.DrawableRes
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import pl.inpost.recruitmenttask.R
import pl.inpost.recruitmenttask.domain.model.Customer
import pl.inpost.recruitmenttask.domain.model.Operations
import pl.inpost.recruitmenttask.domain.model.Shipment
import pl.inpost.recruitmenttask.domain.model.ShipmentStatus
import pl.inpost.recruitmenttask.domain.model.ShipmentType
import pl.inpost.recruitmenttask.presentation.theme.InPostRecruitmentTaskTheme
import pl.inpost.recruitmenttask.presentation.theme.brandingYellow
import pl.inpost.recruitmenttask.presentation.theme.shipmentCardLabel
import pl.inpost.recruitmenttask.presentation.theme.shipmentCardTextBold
import pl.inpost.recruitmenttask.presentation.theme.shipmentCardTextButton
import pl.inpost.recruitmenttask.presentation.theme.shipmentCardTextNormal
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
            Text(
                text = stringResource(id = R.string.shipment_card_number_label),
                style = MaterialTheme.typography.shipmentCardLabel
            )
            Text(
                text = shipmentNumber,
                style = MaterialTheme.typography.shipmentCardTextNormal
            )
        }
        Icon(
            painter = painterResource(id = deliveryIcon),
            contentDescription = null,
            tint = Color.Unspecified
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
            LabelText(
                text = stringResource(id = R.string.shipment_card_status_label)
            )
            Text(
                text = shipmentStatus,
                style = MaterialTheme.typography.shipmentCardTextBold
            )
        }
        if (showExtendedStatus) {
            Column(
                horizontalAlignment = Alignment.End
            ) {
                LabelText(
                    text = shipmentStatus
                )
                Text(
                    text = shipmentFormattedDate,
                    style = MaterialTheme.typography.shipmentCardTextNormal
                )
            }
        }
    }
}

@Composable
private fun ShipmentSenderRow(
    senderName: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }
    var isMenuVisible by rememberSaveable {
        mutableStateOf(false)
    }
    var pressOffset by remember {
        mutableStateOf(DpOffset.Zero)
    }
    var itemHeight by remember {
        mutableStateOf(0.dp)
    }
    val density = LocalDensity.current

    Box(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp)
            .indication(interactionSource, LocalIndication.current)
            .onGloballyPositioned { layoutCoordinates ->
                val globalOffset = layoutCoordinates.positionInRoot()
                pressOffset = with(density) {
                    DpOffset(globalOffset.x.toDp(), globalOffset.y.toDp())
                }
                itemHeight = with(density) { layoutCoordinates.size.height.toDp() }
            }
            .pointerInput(true) {
                detectTapGestures(
                    onLongPress = {
                        isMenuVisible = true
                    },
                    onPress = {
                        val press = PressInteraction.Press(it)
                        interactionSource.emit(press)
                        tryAwaitRelease()
                        interactionSource.emit(PressInteraction.Release(press))
                    }
                )
            }
    ) {
        LabelText(
            modifier = Modifier.align(Alignment.TopStart),
            text = stringResource(R.string.shipment_card_sender_label)
        )
        Text(
            modifier = Modifier.align(Alignment.BottomStart),
            style = MaterialTheme.typography.shipmentCardTextBold,
            text = senderName
        )
        Row(
            modifier = Modifier.align(Alignment.BottomEnd),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.shipment_card_more_button_label),
                style = MaterialTheme.typography.shipmentCardTextButton
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow),
                tint = MaterialTheme.colors.brandingYellow,
                contentDescription = ""
            )
        }
        DropdownMenu(
            expanded = isMenuVisible,
            offset = DpOffset(pressOffset.x, pressOffset.y + itemHeight),
            onDismissRequest = { isMenuVisible = false },
        ) {
            DropdownMenuItem(onClick = {
                onClick()
                isMenuVisible = false
            }) {
                Text(text = "Archiwizuj", style = MaterialTheme.typography.shipmentCardTextButton)
            }
        }
    }
}


@Composable
fun LabelText(
    modifier: Modifier = Modifier,
    text: String
) = Text(
    modifier = modifier,
    text = text,
    style = MaterialTheme.typography.shipmentCardLabel
)

@Preview
@Composable
private fun preview_content() {
    val shipment = Shipment(
        number = "16730345345597442248333",
        type = ShipmentType.LOCKER,
        sender = Customer("sender@example.com", null, null),
        status = ShipmentStatus.READY_TO_PICKUP,
        operations = Operations(false, false, false, false, false, false),
        pickupDate = ZonedDateTime.parse(
            "2022-11-05T04:56:07Z",
            DateTimeFormatter.ISO_OFFSET_DATE_TIME
        ),
        expireDate = null,
        storedDate = null,
    )
    InPostRecruitmentTaskTheme {
        Surface {
            ShipmentCardContent(
                number = shipment.number,
                state = ShipmentCardState(
                    shipment = shipment,
                    resources = LocalContext.current.resources
                ),
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
private fun PreviewShipmentStatusRow() {
    InPostRecruitmentTaskTheme {
        Surface {
            ShipmentStatusRow(
                stringResource(id = R.string.status_ready_to_pickup),
                showExtendedStatus = true,
                shipmentFormattedDate = "pt. | 25.11.22 | 04:56"
            )
        }
    }
}