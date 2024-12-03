package pl.inpost.recruitmenttask.presentation.shipmentList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.inpost.recruitmenttask.network.api.ShipmentApi
import pl.inpost.recruitmenttask.data.model.ShipmentDto
import javax.inject.Inject

@HiltViewModel
class ShipmentListViewModel @Inject constructor(
    private val shipmentApi: ShipmentApi
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state

    init {
//        refreshData()
    }

    fun refreshData() {
        viewModelScope.launch {
            _state.update { old -> old.copy(isLoading = true) }
            val shipments = shipmentApi.getShipments()
            _state.update { old -> old.copy(isLoading = false, shipments = shipments) }
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val shipments: List<ShipmentDto> = emptyList()
    )
}
