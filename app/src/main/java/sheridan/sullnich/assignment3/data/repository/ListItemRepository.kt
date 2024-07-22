package sheridan.sullnich.assignment3.data.repository

import kotlinx.coroutines.flow.Flow
import sheridan.sullnich.assignment3.domain.ListItem

interface ListItemRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllListItemsStream(): Flow<List<ListItem>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getListItemByIdStream(id: Int): Flow<ListItem?>

    /**
     * Insert item in the data source
     */
    suspend fun insertListItem(listItem: ListItem)

    /**
     * Delete item from the data source
     */
    suspend fun deleteListItem(listItem: ListItem)

    suspend fun deleteListItemById(id: Int)

    /**
     * Update item in the data source
     */
    suspend fun updateListItem(listItem: ListItem)

    suspend fun updateListItemSelectedById(id: Int, selected: Boolean)
}