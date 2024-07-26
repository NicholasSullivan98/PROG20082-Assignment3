package sheridan.sullnich.assignment3.ui.item.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import sheridan.sullnich.assignment3.data.repository.ListItemRepository
import sheridan.sullnich.assignment3.ui.item.form.ListItemFormViewModel
import sheridan.sullnich.assignment3.ui.item.form.toListItemFormUiState
import sheridan.sullnich.assignment3.ui.item.model.ListItemFormModel
import sheridan.sullnich.assignment3.ui.navigation.ItemEditDestination
import javax.inject.Inject

@HiltViewModel
class ListItemEditViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val listItemRepository: ListItemRepository
) : ListItemFormViewModel() {

    private val itemId: Int =
        checkNotNull(savedStateHandle[ItemEditDestination.ITEM_ID_ARG])

    init {
        viewModelScope.launch {
            uiState = listItemRepository.getListItemByIdStream(itemId)
                .filterNotNull()
                .first()
                .toListItemFormUiState(isEntryValid = true)
        }
    }

    fun updateListItem() = viewModelScope.launch {
        val formModel: ListItemFormModel = uiState.listItemFormModel
        if (formModel.isValid()) {
            listItemRepository.updateListItem(formModel.toListItem())
        }
    }
}