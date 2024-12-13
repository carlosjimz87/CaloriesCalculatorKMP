package org.carlosjimz87.caloriescalculatorkmm

import CaloriesCalculatorTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import org.carlosjimz87.caloriescalculatorkmm.composables.HumanFormWithThreshold
import org.carlosjimz87.caloriescalculatorkmm.navigation.AppNavGraph
import org.carlosjimz87.caloriescalculatorkmm.screens.dashboard.views.GoalsView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CaloriesCalculatorTheme {
                val navController = rememberNavController()
                CaloriesCalculatorTheme {
                    AppNavGraph(navController = navController)
                    //GoalsView()
                }
            }
        }
    }
}