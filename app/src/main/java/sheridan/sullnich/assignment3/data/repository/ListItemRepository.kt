package sheridan.sullnich.assignment3.data.repository

import kotlinx.coroutines.flow.Flow
import sheridan.sullnich.assignment3.domain.ListItem

interface ListItemRepository {

    fun getAllListItemsStream(): Flow<List<ListItem>>

    fun getListItemByIdStream(id: Int): Flow<ListItem?>

    suspend fun insertListItem(listItem: ListItem)

    suspend fun deleteListItem(listItem: ListItem)

    suspend fun deleteListItemById(id: Int)

    suspend fun updateListItem(listItem: ListItem)

    suspend fun updateListItemSelectedById(id: Int, selected: Boolean)
}