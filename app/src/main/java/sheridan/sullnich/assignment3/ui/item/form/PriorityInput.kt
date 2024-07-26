package sheridan.sullnich.assignment3.ui.item.form

import android.widget.RatingBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun PriorityInput(
    priority: Float,
    onPriorityChange: (Float) -> Unit,
    modifier: Modifier = Modifier
){
    AndroidView(
        factory = { context ->
            RatingBar(context).apply {
                stepSize = 1.0f
                numStars = 3
            }
        },
        update = { ratingBar ->
            ratingBar.rating = priority
            ratingBar.setOnRatingBarChangeListener { _, _, _ ->
                onPriorityChange(ratingBar.rating)
            }
        },
        modifier = modifier
    )
}