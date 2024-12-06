@file:OptIn(ExperimentalFoundationApi::class)

package pl.inpost.recruitmenttask.presentation.shipmentList

import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import pl.inpost.recruitmenttask.domain.model.Shipment
import pl.inpost.recruitmenttask.presentation.theme.dividerText

fun LazyListScope.shipmentsList(
    shipments: Map<Int, List<Shipment>>,
    archiveShipmentAction: (String) -> Unit,
) {
    shipments.forEach {
        stickyHeader {
            ShipmentCategoryDivider(it.key)
        }
        items(
            items = it.value,
            key = { item: Shipment -> item.number }) {
            ShipmentCard(
                shipment = it,
                onClick = { archiveShipmentAction(it.number) },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun ShipmentCategoryDivider(@StringRes labelId: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface)
            .padding(vertical = 8.dp, horizontal = 20.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(id = labelId),
            style = MaterialTheme.typography.dividerText,
        )
    }
}

fun LazyListScope.noShipmentsMessage(
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
