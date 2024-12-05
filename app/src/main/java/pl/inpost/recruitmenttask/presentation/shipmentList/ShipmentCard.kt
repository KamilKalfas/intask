package pl.inpost.recruitmenttask.presentation.shipmentList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.inpost.recruitmenttask.R
import pl.inpost.recruitmenttask.domain.model.Customer
import pl.inpost.recruitmenttask.domain.model.Operations
import pl.inpost.recruitmenttask.domain.model.Shipment
import pl.inpost.recruitmenttask.domain.model.ShipmentStatus
import pl.inpost.recruitmenttask.presentation.theme.InPostRecruitmentTaskTheme

@Composable
fun ShipmentCard(
    shipment: Shipment,
    onClick: () -> Unit,
    modifier: Modifier
) {
    ShipmentCardContent(
        shipment = shipment,
        onClick = onClick,
        modifier = modifier
    )
}

@Composable
private fun ShipmentCardContent(
    shipment: Shipment,
    onClick: () -> Unit,
    modifier: Modifier
) {
    val senderName = remember(shipment.sender) {
        shipment.sender.let { sender ->
            sequenceOf(sender.name, sender.email, sender.phoneNumber).firstOrNull()
        }
    }

    Column(
        modifier = modifier
            .background(MaterialTheme.colors.background)
            .padding(horizontal = 20.dp)
            .padding(top = 16.dp, bottom = 8.dp)
    ) {
        Text("NR PRZESYLKI")
        Text(shipment.number)
        Spacer(modifier = Modifier.height(16.dp))
        Text("STATUS")
        Text(shipment.status.toString())
        Spacer(modifier = Modifier.height(16.dp))
        Text("NADAWCA")
        Text(senderName ?: stringResource(id = R.string.sender_unknown))
    }
}

@Preview
@Composable
private fun preview_content() {
    InPostRecruitmentTaskTheme {
        Surface {
            ShipmentCardContent(
                shipment = Shipment(
                    number = "16730345345597442248333",
                    sender = Customer("sender@example.com", null, null),
                    status = ShipmentStatus.READY_TO_PICKUP,
                    operations = Operations(false, false, false, false, false, false),
                    pickupDate = null,
                    expireDate = null,
                    storedDate = null,
                ),
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
