package sheridan.sullnich.assignment3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import sheridan.sullnich.assignment3.ui.AppRootScreen
import sheridan.sullnich.assignment3.ui.theme.Assignment3Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent{
            Assignment3Theme {
                AppRootScreen()
            }
        }
    }
}