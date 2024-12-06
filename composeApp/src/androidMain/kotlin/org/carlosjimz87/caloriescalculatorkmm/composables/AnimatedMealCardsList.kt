package org.carlosjimz87.caloriescalculatorkmm.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.carlosjimz87.caloriescalculatorkmm.models.CaloriesSection
import org.carlosjimz87.caloriescalculatorkmm.models.Position
import org.carlosjimz87.caloriescalculatorkmm.theme.Blue
import org.carlosjimz87.caloriescalculatorkmm.theme.Green
import org.carlosjimz87.caloriescalculatorkmm.theme.Yellow
import org.carlosjimz87.caloriescalculatorkmm.utils.lighten

@Composable
fun AnimatedMealCardsList(meals: List<CaloriesSection>) {
    val lazyListState = rememberLazyListState()

    LazyColumn(
        state = lazyListState,
        contentPadding = PaddingValues(vertical = 2.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        itemsIndexed(meals) { index, meal ->
            AnimatedVisibility(
                visible = true,
                enter = slideInVertically(
                    initialOffsetY = { it * (index + 1) } // Staggered slide-in effect
                ) + fadeIn(animationSpec = tween(300, delayMillis = index * 100)),
                exit = fadeOut()
            ) {
                MealCard(
                    index = index,
                    title = meal.title,
                    description = meal.desc,
                    calories = meal.calories,
                    color = meal.color.lighten(0.2f),
                    position = Position.fromIndex(index, meals.size)
                )
            }
        }
    }
}

@Preview
@Composable
private fun AnimatedMealCardsListPreview() {
    val meals = listOf(
        CaloriesSection("Breakfast", "Start your day with a nice breakfast", 350, Green),
        CaloriesSection("Lunch", "A light lunch to keep you going", 450, Yellow),
        CaloriesSection("Dinner", "A balanced dinner to end the day", 600, Blue),
    )

    AnimatedMealCardsList(meals)
}