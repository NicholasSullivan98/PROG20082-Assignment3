package sheridan.sullnich.assignment3.ui.item.edit

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import sheridan.sullnich.assignment3.ui.common.ItemListTopAppBar
import sheridan.sullnich.assignment3.ui.item.form.ListItemFormBody
import sheridan.sullnich.assignment3.ui.navigation.ItemEditDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListItemEditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    viewModel: ListItemEditViewModel,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            ItemListTopAppBar(
                title = stringResource(ItemEditDestination.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        ListItemFormBody(
            listItemFormUiState = viewModel.uiState,
            onTitleChange = viewModel::onTitleChange,
            onDescriptionChange = viewModel::onDescriptionChange,
            onCategoryChange = viewModel::onCategoryChange,
            onPriorityChange = viewModel::onPriorityChange,
            onDateChange = viewModel::onDateChange,
            onSaveClick = {
                viewModel.updateListItem()
                navigateBack()
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}