package sheridan.sullnich.assignment3.ui.item.model

import sheridan.sullnich.assignment3.domain.Category
import sheridan.sullnich.assignment3.domain.ListItem
import java.util.Date

data class ListItemFormModel(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val category: Category = Category.PERSONAL,
    val priority: Float = 0.0F,
    val date: Date = Date()
){
    fun isValid(): Boolean =
        title.isNotBlank()

    fun toListItem(): ListItem = ListItem(
        id = id,
        title = title,
        description = description,
        category = category,
        priority = priority,
        date = date
    )
}

fun ListItem.toListItemFormModel(): ListItemFormModel = ListItemFormModel(
    id = id,
    title = title,
    description = description,
    category = category,
    priority = priority,
    date = date
)