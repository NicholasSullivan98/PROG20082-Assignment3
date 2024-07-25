package sheridan.sullnich.assignment3.ui

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import sheridan.sullnich.assignment3.ui.navigation.ItemListNavHost

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppRootScreen(navController: NavHostController = rememberNavController()) {
    ItemListNavHost(navController = navController)
}