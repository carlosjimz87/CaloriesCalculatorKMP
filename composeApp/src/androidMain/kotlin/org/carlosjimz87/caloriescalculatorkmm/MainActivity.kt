package org.carlosjimz87.caloriescalculatorkmm

import CaloriesCalculatorTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.carlosjimz87.caloriescalculatorkmm.composables.AnimatedMealCardsList
import org.carlosjimz87.caloriescalculatorkmm.composables.MainScaffold
import org.carlosjimz87.caloriescalculatorkmm.models.BottomTab
import org.carlosjimz87.caloriescalculatorkmm.models.CaloriesSection
import org.carlosjimz87.caloriescalculatorkmm.screens.dashboard.views.DiaryView
import org.carlosjimz87.caloriescalculatorkmm.screens.dashboard.views.GoalsView
import org.carlosjimz87.caloriescalculatorkmm.screens.dashboard.views.SettingsView
import org.carlosjimz87.caloriescalculatorkmm.screens.dashboard.views.StatsView
import org.carlosjimz87.caloriescalculatorkmm.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CaloriesCalculatorTheme {
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
                    }
                }

            }

        }
    }
}