package sheridan.sullnich.assignment3.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import sheridan.sullnich.assignment3.ui.item.details.ItemDetailsScreen
import sheridan.sullnich.assignment3.ui.item.details.ItemDetailsViewModel
import sheridan.sullnich.assignment3.ui.item.edit.ListItemEditScreen
import sheridan.sullnich.assignment3.ui.item.edit.ListItemEditViewModel
import sheridan.sullnich.assignment3.ui.item.entry.ItemEntryScreen
import sheridan.sullnich.assignment3.ui.item.entry.ItemEntryViewModel
import sheridan.sullnich.assignment3.ui.item.list.ItemListScreen
import sheridan.sullnich.assignment3.ui.item.list.ItemListViewModel

@Composable
fun ItemListNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = ItemListDestination.route,
        modifier = modifier
    ) {
        composable(route = ItemListDestination.route) {
            val viewModel: ItemListViewModel = hiltViewModel()
            ItemListScreen(
                navigateToProductEntry = { navController.navigate(ItemEntryDestination.route) },
                navigateToProductDetails = { id->
                    navController.navigate("${ItemDetailsDestination.route}/${id}")
                },
                viewModel = viewModel
            )
        }
        composable(route = ItemEntryDestination.route) {
            val viewModel: ItemEntryViewModel = hiltViewModel()
            ItemEntryScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() },
                viewModel = viewModel
            )
        }
        composable(
            route = ItemDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(ItemDetailsDestination.ITEM_ID_ARG) {
                type = NavType.IntType
            })
        ) {
            val viewModel: ItemDetailsViewModel = hiltViewModel()
            ItemDetailsScreen(
                navigateToEditItem = { navController.navigate("${ItemEditDestination.route}/$it") },
                navigateBack = { navController.navigateUp() },
                viewModel = viewModel
            )
        }

        composable(
            route = ItemEditDestination.routeWithArgs,
            arguments = listOf(navArgument(ItemEditDestination.ITEM_ID_ARG) {
                type = NavType.IntType
            })
        ) {
            val viewModel: ListItemEditViewModel = hiltViewModel()
            ListItemEditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() },
                viewModel = viewModel
            )
        }
    }
}
