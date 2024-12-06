package org.carlosjimz87.caloriescalculatorkmm.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.carlosjimz87.caloriescalculatorkmm.models.CaloriesSection
import org.carlosjimz87.caloriescalculatorkmm.models.Position
import org.carlosjimz87.caloriescalculatorkmm.theme.Blue
import org.carlosjimz87.caloriescalculatorkmm.theme.Green
import org.carlosjimz87.caloriescalculatorkmm.theme.Orange
import org.carlosjimz87.caloriescalculatorkmm.theme.White
import org.carlosjimz87.caloriescalculatorkmm.theme.Yellow
import org.carlosjimz87.caloriescalculatorkmm.utils.lighten

@Composable
fun AnimatedMealCardsList(
    meals: List<CaloriesSection>,
    animated: Boolean = true,
    headerSlideOffset: Animatable<Float, AnimationVector1D> = Animatable(-100f),
    headerAlpha: Animatable<Float, AnimationVector1D> = Animatable(0f)
) {
    val visibilityStates = remember { mutableStateListOf(*Array(meals.size) { false }) }

    LaunchedEffect(Unit) {
        if(animated) {
            meals.forEachIndexed { index, _ ->
                visibilityStates[index] = true // Trigger visibility for each item
            }
        }
    }

    LazyColumn(
        contentPadding = PaddingValues(vertical = 2.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.fillMaxSize()
            .offset(y = if(animated) headerSlideOffset.value.dp else 0.dp)
            .alpha(alpha = if(animated) headerAlpha.value else 1f)
    ) {
        itemsIndexed(meals) { index, meal ->
            AnimatedVisibility(
                visible = if(animated) visibilityStates[index] else true, // Use dynamic visibility
                enter = slideInVertically(
                    initialOffsetY = { it * (index + 1) } // Staggered slide-in effect
                ) + fadeIn(animationSpec = tween(500, delayMillis = index * 100)),
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

@Preview(showBackground = true)
@Composable
fun AnimatedMealCardsListPreview() {
    // Sample meal data
    val sampleMeals = listOf(
        CaloriesSection("Breakfast", "Oatmeal with fruits and nuts", 450, Green),
        CaloriesSection("Lunch", "Chops with potatoes", 384, Yellow),
        CaloriesSection("Dinner", "No products added yet", 0, Orange),
        CaloriesSection("Other", "No products added yet", 0, Blue)
    )

    // Apply a background to distinguish the animation
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .background(White)
    ) {
        // Call the animated list
        AnimatedMealCardsList(meals = sampleMeals, animated = false)
    }
}