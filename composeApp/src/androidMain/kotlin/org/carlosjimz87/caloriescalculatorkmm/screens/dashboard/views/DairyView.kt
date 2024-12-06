package org.carlosjimz87.caloriescalculatorkmm.screens.dashboard.views

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.carlosjimz87.caloriescalculatorkmm.Constants.meals
import org.carlosjimz87.caloriescalculatorkmm.composables.AnimatedDiaryHeader
import org.carlosjimz87.caloriescalculatorkmm.composables.AnimatedMealCardsList

@Composable
fun DiaryView() {
    val initialHeaderAlpha = 0f
    val initialOffsetHeader = -100f
    val headerDuration = 500

    // Animation states
    val headerSlideOffset = remember { Animatable(initialOffsetHeader) }
    val headerAlpha = remember { Animatable(initialHeaderAlpha) }

    // Trigger animations for the header
    LaunchedEffect(Unit) {
        launch {
            headerSlideOffset.animateTo(
                targetValue = 0f,
                animationSpec = tween(durationMillis = headerDuration, easing = FastOutSlowInEasing)
            )
        }
        launch {
            headerAlpha.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = headerDuration, easing = LinearEasing)
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(vertical = 16.dp)
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header Section
        AnimatedDiaryHeader(headerSlideOffset, headerAlpha)

        // Meal Cards List
        AnimatedMealCardsList(meals = meals, headerSlideOffset = headerSlideOffset, headerAlpha = headerAlpha)
    }
}


@Preview(showBackground = true)
@Composable
fun DiaryViewPreview() {
    DiaryView()
}