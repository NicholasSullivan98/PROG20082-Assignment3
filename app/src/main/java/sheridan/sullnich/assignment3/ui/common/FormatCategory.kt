package sheridan.sullnich.assignment3.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringArrayResource
import sheridan.sullnich.assignment3.R
import sheridan.sullnich.assignment3.domain.Category

@Composable
fun categoryToString(category: Category) =
    stringArrayResource(id = R.array.itemList_category)[category.ordinal]