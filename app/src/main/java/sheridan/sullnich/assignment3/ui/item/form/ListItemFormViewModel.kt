package sheridan.sullnich.assignment3.ui.item.form

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import sheridan.sullnich.assignment3.domain.Category
import java.util.Date

abstract class ListItemFormViewModel() : ViewModel() {
    var uiState: ListItemFormUiState by mutableStateOf(ListItemFormUiState())
        protected set

    fun onTitleChange(newTitle: String){
        val newFormModel = uiState.listItemFormModel.copy(title = newTitle)
        uiState = uiState.copy(
            listItemFormModel = newFormModel,
            isEntryValid = newFormModel.isValid()
        )
    }

    fun onDescriptionChange(newDescription: String) {
        val newFormModel = uiState.listItemFormModel.copy(description = newDescription)
        uiState = uiState.copy(
            listItemFormModel = newFormModel,
            isEntryValid = newFormModel.isValid()
        )
    }

    fun onCategoryChange(newCategory: Category){
        val newFormModel = uiState.listItemFormModel.copy(category = newCategory)
        uiState = uiState.copy(listItemFormModel = newFormModel)
    }

    fun onPriorityChange(newPriority: Float){
        val newFormModel = uiState.listItemFormModel.copy(priority = newPriority)
        uiState = uiState.copy(listItemFormModel = newFormModel)
    }

    fun onDateChange(newDate: Date){
        val newFormModel = uiState.listItemFormModel.copy(date = newDate)
        uiState = uiState.copy(listItemFormModel = newFormModel)
    }

}
