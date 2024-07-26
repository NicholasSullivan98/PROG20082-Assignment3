package sheridan.sullnich.assignment3.ui.item.form

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.viewinterop.AndroidView
import sheridan.sullnich.assignment3.R
import sheridan.sullnich.assignment3.domain.Category

@Composable
fun CategoryInput(
    category: Category,
    onCategoryChange: (Category)->Unit,
    modifier: Modifier = Modifier
){
    val conditions: Array<String> = stringArrayResource(R.array.itemList_category)

    AndroidView(
        modifier = modifier,
        factory = { context ->
            Spinner(context).apply {
                adapter =
                    ArrayAdapter(
                        context,
                        android.R.layout.simple_spinner_dropdown_item,
                        conditions
                    )
            }
        },
        update = { spinner ->
            spinner.setSelection(category.ordinal)
            spinner.onItemSelectedListener = CategorySpinnerAdapter(onCategoryChange)
        }
    )
}

class CategorySpinnerAdapter(
    val onCategoryChange: (Category) -> Unit
) : AdapterView.OnItemSelectedListener {
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        onCategoryChange(Category.entries[position])
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        onCategoryChange(Category.PERSONAL)
    }
}