package sheridan.sullnich.assignment3.ui.item.list

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sheridan.sullnich.assignment3.R
import sheridan.sullnich.assignment3.domain.ListItem
import sheridan.sullnich.assignment3.ui.common.ItemListTopAppBar
import sheridan.sullnich.assignment3.ui.common.categoryToString
import sheridan.sullnich.assignment3.ui.item.model.ItemListItemModel
import sheridan.sullnich.assignment3.ui.item.model.toItemListItemModel
import sheridan.sullnich.assignment3.ui.navigation.ItemListDestination
import sheridan.sullnich.assignment3.ui.theme.Assignment3Theme

/**
 * Entry route for Home screen
 */
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ItemListScreen(
    navigateToProductEntry: () -> Unit,
    navigateToProductDetails: (Int) -> Unit,
    viewModel: ItemListViewModel,
    modifier: Modifier = Modifier
) {
    val itemListUiState: ItemListUiState by viewModel.itemListUiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            ItemListTopAppBar(
                title = stringResource(ItemListDestination.titleRes),
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToProductEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.item_entry_title)
                )
            }
        },
    ) { innerPadding ->
        ItemListBody(
            itemList = itemListUiState.productList,
            onItemClick = navigateToProductDetails,
            onToggleSelect = viewModel::toggleSelect,
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}

@Composable
private fun ItemListBody(
    itemList: List<ItemListItemModel>,
    onItemClick: (Int) -> Unit,
    onToggleSelect: (ItemListItemModel) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (itemList.isEmpty()) {
            Text(
                text = stringResource(R.string.no_item_description),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            ItemList(
                itemList = itemList,
                onItemClick = onItemClick,
                onToggleSelect = onToggleSelect,
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small))
            )
        }
    }
}

@Composable
private fun ItemList(
    itemList: List<ItemListItemModel>,
    onItemClick: (Int) -> Unit,
    onToggleSelect: (ItemListItemModel) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(items = itemList, key = { it.id }) { item ->
            ItemListItem(
                listItemModel = item,
                onToggleSelect = onToggleSelect,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .clickable { onItemClick(item.id) })
        }
    }
}

@Composable
private fun ItemListItem(
    listItemModel: ItemListItemModel,
    onToggleSelect: (ItemListItemModel) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier, elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
            modifier = modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_small))
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = listItemModel.selected,
                    onCheckedChange = { onToggleSelect(listItemModel)}
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
                ){
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = dimensionResource(id = R.dimen.padding_small))
                    ) {
                        Text(
                            text = listItemModel.title,
                            style = MaterialTheme.typography.titleLarge,
                        )
                        PriorityDisplay(
                            priority = listItemModel.priority.toInt()
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(
                                R.string.category_info,
                                categoryToString(category = listItemModel.category),
                            ),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
            Text(
                text = listItemModel.date,
                fontStyle = FontStyle.Italic,
                modifier = modifier
                    .align(Alignment.Start)
                    .padding(start = dimensionResource(id = R.dimen.padding_small))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeBodyPreview() {
    Assignment3Theme {
        ItemListBody(listOf(
            ListItem(1, "Walk Dog", "Take dog on 10 min walk").toItemListItemModel(),
            ListItem(2, "Take out Trash", "Take trash out to the kerb", selected = true).toItemListItemModel(),
            ListItem(3, "Wash Car", "Do a through wash of the car").toItemListItemModel()
        ), onItemClick = {}, onToggleSelect = {})
    }
}

@Preview(showBackground = true)
@Composable
fun HomeBodyEmptyListPreview() {
    Assignment3Theme {
        ItemListBody(listOf(), onItemClick = {}, onToggleSelect = {})
    }
}

@Preview(showBackground = true)
@Composable
fun InventoryItemPreview() {
    Assignment3Theme{
        ItemListItem(
            ListItem(1, "Walk Dog", "Take dog on 10 min walk").toItemListItemModel(),
            onToggleSelect = {}
        )
    }
}