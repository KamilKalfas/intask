package pl.inpost.recruitmenttask.presentation.shipmentList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.inpost.recruitmenttask.domain.exceptions.Failure
import pl.inpost.recruitmenttask.domain.model.Shipment
import pl.inpost.recruitmenttask.domain.usecase.ArchiveShipmentUseCase
import pl.inpost.recruitmenttask.domain.usecase.GetShipmentsGroupedByHighlightedUseCase
import javax.inject.Inject

@HiltViewModel
class ShipmentListViewModel @Inject constructor(
    private val shipmentsGroupedByHighlightedUseCase: GetShipmentsGroupedByHighlightedUseCase,
    private val archiveShipmentUseCase: ArchiveShipmentUseCase
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
            try {
                val result = shipmentsGroupedByHighlightedUseCase.execute()
                _state.update { old ->
                    old.copy(
                        shipmentsReadyToPickup = result.readyToPickup,
                        shipmentsRest = result.rest
                    )
                }
            } catch (failure: Failure) {
                Log.d("ShipmentListViewModel", "Error $failure")
            }
            _state.update { old -> old.copy(isLoading = false,) }
        }
    }

    fun onArchivePackage(shipmentNumber: String) {
        viewModelScope.launch(Dispatchers.IO) {
            archiveShipmentUseCase.execute(shipmentNumber)
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val shipmentsReadyToPickup: List<Shipment> = emptyList(),
        val shipmentsRest: List<Shipment> = emptyList()
    )
}
