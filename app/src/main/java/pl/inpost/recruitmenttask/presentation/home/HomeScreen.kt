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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import pl.inpost.recruitmenttask.presentation.shipmentList.ShipmentListViewModel
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

    HomeContent(
        state = state,
        pullToRefreshState = pullToRefreshState,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun HomeContent(
    state: ShipmentListViewModel.UiState,
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
            shipmentsList(state.shipments)
        }

        PullRefreshIndicator(
            refreshing = state.isLoading,
            state = pullToRefreshState,
            modifier = Modifier.align(Alignment.TopCenter),
            contentColor = MaterialTheme.colors.brandingYellow
        )
    }
}
