package sheridan.sullnich.assignment3.ui.navigation

import sheridan.sullnich.assignment3.R

object ItemDetailsDestination : NavigationDestination {
    override val route = "item_details"
    override val titleRes = R.string.item_details_title
    const val ITEM_ID_ARG = "itemId"
    val routeWithArgs = "$route/{$ITEM_ID_ARG}"
}