package pl.inpost.recruitmenttask.presentation.shipmentList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pl.inpost.recruitmenttask.network.model.ShipmentNetwork

fun LazyListScope.shipmentsList(
    shipments: List<ShipmentNetwork>,
) {
    if (shipments.isEmpty()) noShipmentsMessage()
    else items(
        items = shipments,
        key = { item: ShipmentNetwork -> item.number }) { shipment ->
        ShipmentCard(
            number = shipment.number,
            status = shipment.status,
            sender = shipment.sender.let {
                if (it == null) "no sender"
                else sequenceOf(
                    it.name,
                    it.email,
                    it.phoneNumber
                ).firstOrNull { it != null } ?: "no sender"
            },
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

private fun LazyListScope.noShipmentsMessage(
    modifier: Modifier = Modifier
) {
    item {
        Box(
            modifier = modifier
                .fillParentMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(modifier = Modifier.align(Alignment.Center), text = "Nothing to show")
        }
    }
}
