package pl.inpost.recruitmenttask.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import pl.inpost.recruitmenttask.presentation.components.LoadingIndicator
import pl.inpost.recruitmenttask.presentation.shipmentList.ShipmentListViewModel
import pl.inpost.recruitmenttask.presentation.shipmentList.ShipmentsList

@Composable
fun HomeScreen(
    modifier: Modifier,
    viewModel: ShipmentListViewModel = viewModel()
) {
    val state = viewModel.state.collectAsState().value
    HomeContent(
        state = state,
        modifier = modifier
    )
}

@Composable
private fun HomeContent(
    state: ShipmentListViewModel.UiState,
    modifier: Modifier
) {
    if (state.isLoading) LoadingIndicator(modifier = modifier)
    else if (state.shipments.isEmpty()) NoShipments(modifier = modifier)
    else ShipmentsList(
        shipments = state.shipments,
        modifier = modifier
    )
}

@Composable
private fun NoShipments(
    modifier: Modifier
) = Column(
    modifier = modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
) {
    Text(text = "Nothing to show")
}
