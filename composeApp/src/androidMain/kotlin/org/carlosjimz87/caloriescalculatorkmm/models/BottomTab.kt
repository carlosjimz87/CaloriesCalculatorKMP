package org.carlosjimz87.caloriescalculatorkmm.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

enum class BottomTab(val title: String, val icon: ImageVector) {
    Diary("Diary", Icons.Filled.DateRange),
    Statistics("Statistics", Icons.Filled.Star),
    Goals("Goals", Icons.Filled.Check),
    Settings("Settings", Icons.Filled.Settings)
}