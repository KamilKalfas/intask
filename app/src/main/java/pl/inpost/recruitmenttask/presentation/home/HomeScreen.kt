package pl.inpost.recruitmenttask.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import pl.inpost.recruitmenttask.R
import pl.inpost.recruitmenttask.domain.model.Shipment
import pl.inpost.recruitmenttask.presentation.shipmentList.ShipmentListViewModel
import pl.inpost.recruitmenttask.presentation.shipmentList.noShipmentsMessage
import pl.inpost.recruitmenttask.presentation.shipmentList.shipmentsList
import pl.inpost.recruitmenttask.presentation.theme.brandingYellow

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier,
    viewModel: ShipmentListViewModel = viewModel()
) {
    val state = viewModel.state.collectAsState().value
    val pullToRefreshState = rememberPullRefreshState(
        refreshing = state.isLoading,
        onRefresh = { viewModel.refreshData() },
        refreshingOffset = 40.dp,
        refreshThreshold = 40.dp
    )
    val homeScreenState = rememberHomeScreenState(state)
    HomeContent(
        isLoading = state.isLoading,
        state = homeScreenState,
        pullToRefreshState = pullToRefreshState,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun HomeContent(
    isLoading: Boolean,
    state: HomeScreenState,
    pullToRefreshState: PullRefreshState,
    modifier: Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(state = pullToRefreshState)
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface)
        ) {
            if (state.hasNoShipments) noShipmentsMessage()
            else shipmentsList(state.shipments)
        }

        PullRefreshIndicator(
            refreshing = isLoading,
            state = pullToRefreshState,
            modifier = Modifier.align(Alignment.TopCenter),
            contentColor = MaterialTheme.colors.brandingYellow
        )
    }
}

@Composable
private fun rememberHomeScreenState(state: ShipmentListViewModel.UiState) : HomeScreenState {
    return remember(
        state.shipmentsReadyToPickup,
        state.shipmentsRest
    ) {
        HomeScreenState(state.shipmentsReadyToPickup, state.shipmentsRest)
    }
}

@Stable
class HomeScreenState(
    shipmentsReadyToPickup: List<Shipment>,
    shipmentsRest: List<Shipment>
) {
    val shipments: Map<Int, List<Shipment>> = mapOf(
        R.string.shipments_category_ready_to_pickup to shipmentsReadyToPickup,
        R.string.shipments_category_rest to shipmentsRest
    )
    val hasNoShipments: Boolean = shipments.values.flatten().isEmpty()
}
