package sheridan.sullnich.assignment3.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ListItemDao {

    @Query("SELECT * from listItems ORDER BY title ASC")
    fun getAllListItemsStream(): Flow<List<LocalListItem>>

    @Query("SELECT * from listItems WHERE id = :id")
    fun getListItemByIdStream(id: Int): Flow<LocalListItem?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertListItem(listItem: LocalListItem)

    @Update
    suspend fun updateListItem(listItem: LocalListItem)

    @Query("UPDATE listItems SET selected = :selected WHERE id = :id")
    suspend fun updateListItemSelectedById(id: Int, selected: Boolean)

    @Delete
    suspend fun deleteListItem(item: LocalListItem)

    @Query("DELETE FROM listItems WHERE id = :id")
    suspend fun deleteListItemById(id: Int)
}