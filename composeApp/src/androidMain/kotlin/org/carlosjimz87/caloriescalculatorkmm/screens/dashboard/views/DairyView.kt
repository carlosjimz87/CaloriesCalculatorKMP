package org.carlosjimz87.caloriescalculatorkmm.screens.dashboard.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.carlosjimz87.caloriescalculatorkmm.Constants.meals
import org.carlosjimz87.caloriescalculatorkmm.composables.AnimatedDiaryHeader
import org.carlosjimz87.caloriescalculatorkmm.composables.AnimatedMealCardsList

@Composable
fun DiaryView() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(vertical = 16.dp)
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header Section
        AnimatedDiaryHeader()

        // Meal Cards List
        AnimatedMealCardsList(meals = meals, true)
    }
}


@Preview(showBackground = true)
@Composable
fun DiaryViewPreview() {
    DiaryView()
}