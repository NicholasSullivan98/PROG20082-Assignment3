package sheridan.sullnich.assignment3.ui.item.form

import sheridan.sullnich.assignment3.domain.ListItem
import sheridan.sullnich.assignment3.ui.item.model.ListItemFormModel
import sheridan.sullnich.assignment3.ui.item.model.toListItemFormModel

data class ListItemFormUiState(
    val listItemFormModel: ListItemFormModel = ListItemFormModel(),
    val isEntryValid: Boolean = false
)

fun ListItem.toListItemFormUiState(isEntryValid: Boolean = false): ListItemFormUiState =
    ListItemFormUiState(
        listItemFormModel = this.toListItemFormModel(),
        isEntryValid = isEntryValid
    )