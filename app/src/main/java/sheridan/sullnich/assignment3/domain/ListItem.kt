package sheridan.sullnich.assignment3.domain

import androidx.compose.foundation.MutatePriority
import java.util.Date

class ListItem (
    val id: Int = 0,
    val title: String = "No Name",
    val description: String = "No Description",
    val priority: Float = 0.0F,
    val category: Category = Category.PERSONAL,
    val selected: Boolean = false,
    val date: Date = Date()
)