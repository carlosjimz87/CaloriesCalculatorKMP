package org.carlosjimz87.caloriescalculatorkmm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.carlosjimz87.caloriescalculatorkmm.composables.MainScaffold
import org.carlosjimz87.caloriescalculatorkmm.models.BottomTab
import org.carlosjimz87.caloriescalculatorkmm.screens.dashboard.views.DiaryView
import org.carlosjimz87.caloriescalculatorkmm.screens.dashboard.views.GoalsView
import org.carlosjimz87.caloriescalculatorkmm.screens.dashboard.views.SettingsView
import org.carlosjimz87.caloriescalculatorkmm.screens.dashboard.views.StatsView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var selectedTabIndex by remember { mutableIntStateOf(0) }

            MainScaffold(
                selectedTabIndex = selectedTabIndex,
                onTabSelectedIndex = { selectedTabIndex = it }
            ) {
                when (BottomTab.entries[selectedTabIndex]) {
                    BottomTab.Diary -> DiaryView()
                    BottomTab.Stats -> StatsView()
                    BottomTab.Goals -> GoalsView()
                    BottomTab.Preferences -> SettingsView()
                    else -> throw IllegalStateException("Unknown tab selected")
                }
            }
        }
    }
}