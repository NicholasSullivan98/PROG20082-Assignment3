package sheridan.sullnich.assignment3.ui.item.details

import sheridan.sullnich.assignment3.domain.ListItem
import sheridan.sullnich.assignment3.ui.item.model.ItemDetailsModel
import sheridan.sullnich.assignment3.ui.item.model.toItemDetailsModel

/**
 * UI state for ItemDetailsScreen
 */
data class ItemDetailsUiState(
    val detailsModel: ItemDetailsModel
){
    constructor(listItem: ListItem): this (
        detailsModel = listItem.toItemDetailsModel()
    )

    constructor(): this(ListItem())
}