package sheridan.sullnich.assignment3.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import sheridan.sullnich.assignment3.domain.Category
import java.util.Date

@Entity(tableName = "listItems")
data class LocalListItem (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val priority: Float,
    val category: Category,
    val selected: Boolean,
    @ColumnInfo(name = "time_stamp") val date: Date
)