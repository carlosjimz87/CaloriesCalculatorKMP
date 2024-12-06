package org.carlosjimz87.caloriescalculatorkmm.screens.dashboard.views

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.carlosjimz87.caloriescalculatorkmm.Constants.caloriesList
import org.carlosjimz87.caloriescalculatorkmm.Constants.meals
import org.carlosjimz87.caloriescalculatorkmm.composables.MealCard
import org.carlosjimz87.caloriescalculatorkmm.models.Position
import org.carlosjimz87.caloriescalculatorkmm.theme.Gray
import org.carlosjimz87.caloriescalculatorkmm.utils.lighten

@Composable
fun DiaryView() {
    val initialHeaderAlpha = 0f
    val initialOffsetCards = -300f
    val initialOffsetHeader = -100f
    val initialSpreadFraction = 0f
    val headerDuration = 500
    val cardsDuration = 900
    val spreadDuration = 300
    val spreadDelay = 900

    // Animation states
    val headerSlideOffset = remember { Animatable(initialOffsetHeader) }
    val headerAlpha = remember { Animatable(initialHeaderAlpha) }

    val cardSlideOffset = remember { Animatable(initialOffsetCards) }
    val spreadProgress = remember { Animatable(initialSpreadFraction) }

    // Trigger animations
    LaunchedEffect(Unit) {
        coroutineScope {
            launch {
                // Header animations
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

            // Cards animations
            launch {
                cardSlideOffset.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(durationMillis = cardsDuration, easing = FastOutSlowInEasing)
                )
            }
            launch {
                spreadProgress.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(durationMillis = spreadDuration, delayMillis = spreadDelay, easing = LinearOutSlowInEasing)
                )
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
    ) {
        // Header Section
        Text(
            text = "Daily calories",
            style = MaterialTheme.typography.displayLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .offset(y = headerSlideOffset.value.dp)
                .alpha(headerAlpha.value)
                .padding(bottom = 8.dp)
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp)
                .offset(y = headerSlideOffset.value.dp)
                .alpha(headerAlpha.value)
        ) {
            caloriesList.forEach { (label, value) ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.headlineSmall,
                        color = Gray
                    )
                    Text(
                        text = value,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Meal Cards
        meals.forEachIndexed { index, (title, description, calories, color) ->
            MealCard(
                index = index,
                title = title,
                description = description,
                calories = calories,
                color = color.lighten(0.2f),
                position = Position.fromIndex(index, meals.size),
                initialOffset = initialOffsetCards,
                slideOffset = cardSlideOffset.value,
                spreadProgress = spreadProgress.value,
                spreadValue = 1f
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DiaryViewPreview() {
    DiaryView()
}