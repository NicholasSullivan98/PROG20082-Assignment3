package sheridan.sullnich.assignment3.ui.item.entry

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import sheridan.sullnich.assignment3.domain.ListItem
import sheridan.sullnich.assignment3.ui.common.ItemListTopAppBar
import sheridan.sullnich.assignment3.ui.item.form.ListItemFormBody
import sheridan.sullnich.assignment3.ui.item.form.ListItemFormUiState
import sheridan.sullnich.assignment3.ui.item.model.ListItemFormModel
import sheridan.sullnich.assignment3.ui.navigation.ItemEntryDestination
import sheridan.sullnich.assignment3.ui.theme.Assignment3Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemEntryScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    canNavigateBack: Boolean = true,
    viewModel: ItemEntryViewModel
) {
    Scaffold(
        topBar = {
            ItemListTopAppBar(
                title = stringResource(ItemEntryDestination.titleRes),
                canNavigateBack = canNavigateBack,
                navigateUp = onNavigateUp
            )
        }
    ) { innerPadding ->
        ListItemFormBody(
            listItemFormUiState = viewModel.uiState,
            onTitleChange = viewModel::onTitleChange,
            onDescriptionChange = viewModel::onDescriptionChange,
            onCategoryChange = viewModel::onCategoryChange,
            onPriorityChange = viewModel::onPriorityChange,
            onDateChange = viewModel::onDateChange,
            onSaveClick = {
                viewModel.saveItem()
                navigateBack()
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductEntryScreenPreview() {
    Assignment3Theme {
        ListItemFormBody(
            listItemFormUiState = ListItemFormUiState(
                ListItemFormModel(
                    title = "Item name", description = "description"
                )
            ),
            onTitleChange = {}, onDescriptionChange = {},
            onCategoryChange = {}, onPriorityChange = {}, onDateChange = {},
            onSaveClick = {}
        )
    }
}