package com.github.marcusdev01.gscarbono

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.github.marcusdev01.gscarbono.navigation.NavGraph
import com.github.marcusdev01.gscarbono.ui.theme.GsCarbonoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GsCarbonoTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}
