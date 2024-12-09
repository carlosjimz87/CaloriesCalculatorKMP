package org.carlosjimz87.caloriescalculatorkmm.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector


enum class BottomTab(val title: String, val icon: ImageVector) {
        Diary("Diary", Icons.Filled.DateRange),
        Stats("Stats", Icons.Filled.Star),
        Goals("Goals", Icons.Filled.Check),
        Prefs("Prefs", Icons.Filled.Settings)
}