package org.carlosjimz87.caloriescalculatorkmm.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.carlosjimz87.caloriescalculatorkmm.composables.MainScaffold
import org.carlosjimz87.caloriescalculatorkmm.models.BottomTab
import org.carlosjimz87.caloriescalculatorkmm.screens.auth.AuthScreen
import org.carlosjimz87.caloriescalculatorkmm.screens.dashboard.DashboardScreen
import org.carlosjimz87.caloriescalculatorkmm.screens.dashboard.views.DiaryView
import org.carlosjimz87.caloriescalculatorkmm.screens.dashboard.views.GoalsView
import org.carlosjimz87.caloriescalculatorkmm.screens.dashboard.views.PreferencesView
import org.carlosjimz87.caloriescalculatorkmm.screens.dashboard.views.StatsView

@Composable
fun AppNavGraph(navController: NavHostController, startDestination : String = NavDestinations.Login.route) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(NavDestinations.Login.route) {
            AuthScreen(
                isRegister = false,
                onSuccessLogin = {
                    navController.navigate(NavDestinations.Dashboard.route) {
                        popUpTo(NavDestinations.Login.route) { inclusive = true }
                    }
                })
        }

        composable(NavDestinations.Register.route) {
            AuthScreen(
                isRegister = true,
                onSuccessRegister = {
                    navController.navigate(NavDestinations.Dashboard.route) {
                        popUpTo(NavDestinations.Register.route) { inclusive = true }
                    }
                })
        }

        composable(NavDestinations.Dashboard.route) {
            DashboardScreen()
        }
    }
}