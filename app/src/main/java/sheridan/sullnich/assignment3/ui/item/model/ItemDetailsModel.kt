package sheridan.sullnich.assignment3.ui.item.model

import sheridan.sullnich.assignment3.domain.Category
import sheridan.sullnich.assignment3.domain.ListItem
import java.util.Date

data class ItemDetailsModel(
    val id: Int,
    val title: String,
    val description: String,
    val category: Category,
    val priority: Float,
    val date: Date
){
    constructor(listItem: ListItem): this(
        id = listItem.id,
        title = listItem.title,
        description = listItem.description,
        category = listItem.category,
        priority = listItem.priority,
        date = listItem.date
    )
}

fun ListItem.toItemDetailsModel() = ItemDetailsModel(this)