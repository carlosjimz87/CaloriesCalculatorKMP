package org.carlosjimz87.caloriescalculatorkmm.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

enum class BottomTab(val title: String, val icon: ImageVector) {
    Diary("Diary", Icons.Filled.DateRange),
    Stats("Stats", Icons.Filled.Star),
    Goals("Goals", Icons.Filled.Check),
    Preferences("Prefs", Icons.Filled.Settings)
}