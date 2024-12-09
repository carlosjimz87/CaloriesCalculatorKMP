package org.carlosjimz87.caloriescalculatorkmm.screens.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.carlosjimz87.caloriescalculatorkmm.composables.MainScaffold
import org.carlosjimz87.caloriescalculatorkmm.models.BottomTab
import org.carlosjimz87.caloriescalculatorkmm.navigation.NavDestinations
import org.carlosjimz87.caloriescalculatorkmm.screens.dashboard.views.DiaryView
import org.carlosjimz87.caloriescalculatorkmm.screens.dashboard.views.GoalsView
import org.carlosjimz87.caloriescalculatorkmm.screens.dashboard.views.PreferencesView
import org.carlosjimz87.caloriescalculatorkmm.screens.dashboard.views.StatsView

@Composable
fun DashboardScreen(
    initTabIndex : Int = 0,
) {
    var selectedTabIndex by remember { mutableIntStateOf(initTabIndex) }

    MainScaffold(
        tabSelected = BottomTab.entries[selectedTabIndex],
        onTabSelectedIndex = { selectedTabIndex = it }
    ) {
        when (BottomTab.entries[selectedTabIndex]) {
            BottomTab.Diary -> DiaryView()
            BottomTab.Stats -> StatsView()
            BottomTab.Goals -> GoalsView()
            BottomTab.Prefs -> PreferencesView()
        }
    }
}