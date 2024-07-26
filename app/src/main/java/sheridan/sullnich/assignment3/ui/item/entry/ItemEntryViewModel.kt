package sheridan.sullnich.assignment3.ui.item.entry

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import sheridan.sullnich.assignment3.data.repository.ListItemRepository
import sheridan.sullnich.assignment3.ui.item.form.ListItemFormViewModel
import javax.inject.Inject

@HiltViewModel
class ItemEntryViewModel @Inject constructor(
    private val listItemRepository: ListItemRepository
) : ListItemFormViewModel() {

    fun saveItem() = viewModelScope.launch{
        if (uiState.listItemFormModel.isValid()) {
            listItemRepository.insertListItem(uiState.listItemFormModel.toListItem())
        }
    }
}