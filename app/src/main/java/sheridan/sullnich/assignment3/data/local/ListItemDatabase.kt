package sheridan.sullnich.assignment3.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [LocalListItem::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ListItemDatabase : RoomDatabase() {

    abstract fun listItemDao(): ListItemDao

}