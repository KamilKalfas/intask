package pl.inpost.recruitmenttask.presentation.shipmentList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pl.inpost.recruitmenttask.network.model.ShipmentNetwork

@Composable
fun ShipmentsList(
    shipments: List<ShipmentNetwork>,
    modifier: Modifier
) = LazyColumn(
    verticalArrangement = Arrangement.spacedBy(8.dp),
    modifier = modifier.fillMaxSize().background(MaterialTheme.colors.surface)
) {
    items(
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
