package org.carlosjimz87.caloriescalculatorkmm.screens.dashboard.views

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.carlosjimz87.caloriescalculatorkmm.Constants.caloriesList
import org.carlosjimz87.caloriescalculatorkmm.Constants.meals
import org.carlosjimz87.caloriescalculatorkmm.models.Position
import org.carlosjimz87.caloriescalculatorkmm.theme.DarkGray
import org.carlosjimz87.caloriescalculatorkmm.theme.Gray
import org.carlosjimz87.caloriescalculatorkmm.theme.White
import org.carlosjimz87.caloriescalculatorkmm.utils.getShapeFromPosition
import org.carlosjimz87.caloriescalculatorkmm.utils.lighten

@Composable
fun DiaryView() {
    val scope = rememberCoroutineScope()

    // Define animated progress for header and meal cards
    val headerSlideOffset = remember { Animatable(-100f) } // Starts outside the screen (upward)
    val headerAlpha = remember { Animatable(0f) } // Starts fully transparent

    val cardSlideOffset = remember { Animatable(-300f) } // Starts outside the screen (upward)
    val cardSpreadOffset = remember { Animatable(0f) } // Cards overlap initially

    // Trigger animations on composition
    LaunchedEffect(Unit) {
        scope.launch {
            launch {
                headerSlideOffset.animateTo(
                    targetValue = 0f, // Slide to its final position
                    animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
                )
                headerAlpha.animateTo(
                    targetValue = 1f, // Fade to fully visible
                    animationSpec = tween(durationMillis = 500, easing = LinearEasing)
                )
            }
            launch {
                cardSlideOffset.animateTo(
                    targetValue = 0f, // Slide to its final position
                    animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
                )
            }
            launch {
                cardSpreadOffset.animateTo(
                    targetValue = 1f, // Spread to their final position
                    animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)
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
        // Header Section with animations
        Spacer(modifier = Modifier.height(16.dp))
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
            modifier = Modifier.fillMaxWidth()
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

        // Meal Cards with animations

        meals.forEachIndexed { index, (title, description, calories, color) ->
            MealCard(
                first = index == 0,
                title = title,
                description = description,
                calories = calories,
                color = color.lighten(0.2f),
                position = Position.fromIndex(index, meals.size),
                slideOffset = cardSlideOffset.value,
                spreadProgress = cardSpreadOffset.value,
                headerAlpha = headerAlpha.value,
                index = index
            )
        }
    }
}

@Composable
fun MealCard(
    title: String,
    description: String,
    calories: Int,
    color: Color,
    first: Boolean = false,
    position: Position,
    slideOffset: Float,
    spreadProgress: Float,
    headerAlpha: Float,
    index: Int
) {
    val shape = getShapeFromPosition(position)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .height(100.dp)
            //.alpha(headerAlpha)
            .offset(y = (slideOffset + (index * -spreadProgress)).dp) // Slide and spread effect
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = color),
            shape = shape,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = White
                    )
                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        color = DarkGray
                    )
                }
                Text(
                    text = "$calories kcal",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = White
                )
            }
        }

        if (!first) {
            Canvas(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(50.dp)
            ) {
                val trianglePath = Path().apply {
                    moveTo(size.width, 1f)
                    lineTo(size.width, -70f)
                    quadraticTo(
                        size.width / 2 + 40, size.height / 2 - 70,
                        0f, 0f
                    )
                    close()
                }
                drawPath(
                    path = trianglePath,
                    color = color
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DiaryViewPreview() {
    DiaryView()
}