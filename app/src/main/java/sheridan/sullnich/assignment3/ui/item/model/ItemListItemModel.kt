package sheridan.sullnich.assignment3.ui.item.model

import sheridan.sullnich.assignment3.domain.Category
import sheridan.sullnich.assignment3.domain.ListItem
import sheridan.sullnich.assignment3.ui.common.formatDateTime

data class ItemListItemModel(
    val id: Int,
    val title: String,
    val description: String,
    val selected: Boolean,
    val category: Category,
    val priority: Float,
    val date: String
){
    constructor(listItem: ListItem): this(
        id = listItem.id,
        title = listItem.title,
        description = listItem.description,
        selected = listItem.selected,
        category = listItem.category,
        priority = listItem.priority,
        date = formatDateTime(listItem.date)
    )
}

fun ListItem.toItemListItemModel() = ItemListItemModel(this)