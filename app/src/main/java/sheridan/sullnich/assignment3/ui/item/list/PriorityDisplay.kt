package sheridan.sullnich.assignment3.ui.item.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import sheridan.sullnich.assignment3.R

@Composable
fun PriorityDisplay(priority: Int, modifier: Modifier = Modifier) {
    val displayDescription = pluralStringResource(R.plurals.number_of_stars, count = priority)
    Row(
        modifier.semantics {
            contentDescription = displayDescription
        }
    ) {
        for(i: Int in 1..3){
            Image(
                modifier = Modifier.size(32.dp),
                painter = painterResource(
                    if(priority >= i) R.drawable.green_star else R.drawable.grey_star
                ),
                contentDescription = null
            )
        }
    }
}