package org.carlosjimz87.caloriescalculatorkmm.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import org.carlosjimz87.caloriescalculatorkmm.models.Position
import org.carlosjimz87.caloriescalculatorkmm.theme.Blue
import org.carlosjimz87.caloriescalculatorkmm.theme.DarkGray
import org.carlosjimz87.caloriescalculatorkmm.theme.Green
import org.carlosjimz87.caloriescalculatorkmm.theme.White
import org.carlosjimz87.caloriescalculatorkmm.theme.Yellow
import org.carlosjimz87.caloriescalculatorkmm.utils.getShapeFromPosition

@Composable
fun MealCard(
    index: Int,
    title: String,
    description: String,
    calories: Int,
    color: Color,
    position: Position,
    initialOffset: Float,
    slideOffset: Float,
    spreadProgress: Float,
    spreadValue: Float,
) {
    val shape = getShapeFromPosition(position)

    // Combined slide and spread offset
    val offsetY = lerp(
        start = initialOffset,
        stop = index * spreadValue,
        fraction = spreadProgress
    )
    val combinedOffsetY = slideOffset + offsetY

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .height(100.dp)
            .offset(y = combinedOffsetY.dp)
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

        if (index > 0) {
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

@Preview
@Composable
private fun MealCardPreviewTop() {
    MealCard(
        index = 0,
        title = "Breakfast",
        description = "Oatmeal with fruits",
        calories = 350,
        color = Green,
        position = Position.TOP,
        initialOffset = 0f,
        slideOffset = 0f,
        spreadProgress = 0f,
        spreadValue = 100f
    )
}

@Preview
@Composable
private fun MealCardPreviewMiddle() {
    MealCard(
        index = 1,
        title = "Dinner",
        description = "Chops with potatoes",
        calories = 483,
        color = Yellow,
        position = Position.MIDDLE,
        initialOffset = 0f,
        slideOffset = 0f,
        spreadProgress = 0f,
        spreadValue = 100f
    )
}

@Preview
@Composable
private fun MealCardPreviewBottom() {
    MealCard(
        index = 3,
        title = "Lunch",
        description = "No products added yet",
        calories = 0,
        color = Blue,
        position = Position.BOTTOM,
        initialOffset = 0f,
        slideOffset = 0f,
        spreadProgress = 0f,
        spreadValue = 100f
    )
}