package sheridan.sullnich.assignment3.ui.item.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import sheridan.sullnich.assignment3.data.repository.ListItemRepository
import sheridan.sullnich.assignment3.ui.item.model.ItemListItemModel
import sheridan.sullnich.assignment3.ui.item.model.toItemListItemModel
import javax.inject.Inject

@HiltViewModel
class ItemListViewModel @Inject constructor(
    private val listItemRepository: ListItemRepository
) : ViewModel() {

    val itemListUiState: StateFlow<ItemListUiState> =
        listItemRepository.getAllListItemsStream()
            .map { list -> ItemListUiState(list.map { item -> item.toItemListItemModel() }) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ItemListUiState()
            )

    fun toggleSelect(listItemModel: ItemListItemModel) {
        viewModelScope.launch {
            listItemRepository.updateListItemSelectedById(listItemModel.id, !listItemModel.selected)
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}