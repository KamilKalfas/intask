package pl.inpost.recruitmenttask.presentation.shipmentList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.inpost.recruitmenttask.R
import pl.inpost.recruitmenttask.domain.model.Shipment
import pl.inpost.recruitmenttask.domain.usecase.GetShipmentsGroupedByHighlightedUseCase
import pl.inpost.recruitmenttask.domain.usecase.GroupedShipmentsResult
import javax.inject.Inject

@HiltViewModel
class ShipmentListViewModel @Inject constructor(
    private val shipmentsGroupedByHighlightedUseCase: GetShipmentsGroupedByHighlightedUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state

    init {
        refreshData()
    }

    fun refreshData() {
        // dispatchers IO because we pretend this is network call
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { old -> old.copy(isLoading = true) }

            when (val result = shipmentsGroupedByHighlightedUseCase.execute()) {
                is GroupedShipmentsResult.Error -> {
                    // TODO() add some error UI information
                }

                is GroupedShipmentsResult.Success -> {
                    _state.update { old ->
                        old.copy(
                            isLoading = false,
                            shipmentsReadyToPickup = result.readyToPickup,
                            shipmentsRest = result.rest
                        )
                    }
                }
            }

        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val shipmentsReadyToPickup: List<Shipment> = emptyList(),
        val shipmentsRest: List<Shipment> = emptyList()
    )
}
