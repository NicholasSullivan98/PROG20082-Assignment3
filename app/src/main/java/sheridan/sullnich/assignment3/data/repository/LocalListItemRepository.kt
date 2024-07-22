package sheridan.sullnich.assignment3.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import sheridan.sullnich.assignment3.data.local.ListItemDao
import sheridan.sullnich.assignment3.data.local.LocalListItem
import sheridan.sullnich.assignment3.domain.ListItem

@OptIn(DelicateCoroutinesApi::class)
class LocalListItemRepository(
    private val listItemDao: ListItemDao,
    private val externalScope: CoroutineScope,
    private val dispatcher: CoroutineDispatcher
) : ListItemRepository {

    constructor(listItemDao: ListItemDao) : this(listItemDao, GlobalScope, Dispatchers.IO)

    override fun getAllListItemsStream(): Flow<List<ListItem>> =
        listItemDao.getAllListItemsStream()
            .map{ list -> list.map { localItem ->  localItem.toListItem() }}
            .flowOn(dispatcher)

    override fun getListItemByIdStream(id: Int): Flow<ListItem?> =
        listItemDao.getListItemByIdStream(id)
            .map { localItem -> localItem?.toListItem() }
            .flowOn(dispatcher)

    override suspend fun insertListItem(listItem: ListItem) {
        externalScope.launch(dispatcher) { listItemDao.insertListItem(listItem.toLocalListItem()) }.join()
    }

    override suspend fun deleteListItem(listItem: ListItem) {
        externalScope.launch(dispatcher) { listItemDao.deleteListItem(listItem.toLocalListItem()) }.join()
    }

    override suspend fun deleteListItemById(id: Int){
        externalScope.launch(dispatcher) { listItemDao.deleteListItemById(id) }.join()
    }

    override suspend fun updateListItem(listItem: ListItem) {
        externalScope.launch(dispatcher) { listItemDao.updateListItem(listItem.toLocalListItem()) }.join()
    }

    override suspend fun updateListItemSelectedById(id: Int, selected: Boolean) {
        externalScope.launch(dispatcher) { listItemDao.updateListItemSelectedById(id, selected) }.join()
    }
}

fun LocalListItem.toListItem(): ListItem = ListItem(
    id = this.id,
    title = this.title,
    description = this.description,
    category = this.category,
    priority = this.priority,
    selected = this.selected,
    date = this.date
)

fun ListItem.toLocalListItem(): LocalListItem = LocalListItem(
    id = this.id,
    title = this.title,
    description = this.description,
    category = this.category,
    priority = this.priority,
    selected = this.selected,
    date = this.date
)
