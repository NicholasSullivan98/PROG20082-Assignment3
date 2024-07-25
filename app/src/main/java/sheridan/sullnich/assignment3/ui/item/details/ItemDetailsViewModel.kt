package sheridan.sullnich.assignment3.ui.item.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import sheridan.sullnich.assignment3.data.repository.ListItemRepository
import sheridan.sullnich.assignment3.ui.navigation.ItemDetailsDestination
import javax.inject.Inject

@HiltViewModel
class ItemDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val listItemRepository: ListItemRepository,
) : ViewModel() {

    private val productId: Int = checkNotNull(savedStateHandle[ItemDetailsDestination.ITEM_ID_ARG])

    /**
     * Holds the item details ui state. The data is retrieved from [ListItemRepository] and mapped to
     * the UI state.
     */
    val uiState: StateFlow<ItemDetailsUiState> =
        listItemRepository.getListItemByIdStream(productId)
            .filterNotNull()
            .map { item ->
                ItemDetailsUiState(item)
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ItemDetailsUiState()
            )

    /**
     * Reduces the item quantity by one and update the [ListItemRepository]'s data source.
     */
    /*
    fun reduceQuantityByOne() {
        viewModelScope.launch {
            val detailsModel = uiState.value.detailsModel
            if (detailsModel.quantity > 0) {
                productRepository.updateProductQuantityById(detailsModel.id, detailsModel.quantity - 1)
            }
        }
    }
     */

    /**
     * Deletes the item from the [ListItemRepository]'s data source.
     */
    fun deleteListItem() = viewModelScope.launch{
        listItemRepository.deleteListItemById(uiState.value.detailsModel.id)
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}