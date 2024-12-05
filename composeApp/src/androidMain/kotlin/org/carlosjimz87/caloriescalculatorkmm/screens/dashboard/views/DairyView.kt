package org.carlosjimz87.caloriescalculatorkmm.screens.dashboard.views

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import org.carlosjimz87.caloriescalculatorkmm.models.CaloriesSection
import org.carlosjimz87.caloriescalculatorkmm.models.Position
import org.carlosjimz87.caloriescalculatorkmm.theme.Blue
import org.carlosjimz87.caloriescalculatorkmm.theme.DarkGray
import org.carlosjimz87.caloriescalculatorkmm.theme.Gray
import org.carlosjimz87.caloriescalculatorkmm.theme.Green
import org.carlosjimz87.caloriescalculatorkmm.theme.Orange
import org.carlosjimz87.caloriescalculatorkmm.theme.White
import org.carlosjimz87.caloriescalculatorkmm.theme.Yellow
import org.carlosjimz87.caloriescalculatorkmm.utils.getShapeFromPosition
import org.carlosjimz87.caloriescalculatorkmm.utils.lighten

@Composable
fun DiaryView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Header Section
        Text(
            text = "Daily calories",
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            listOf(
                "Fats" to "31g",
                "Carb." to "66g",
                "Proteins" to "32g",
                "Calories" to "900kcal"
            ).forEach { (label, value) ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.bodyLarge,
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
        val meals = listOf(
            CaloriesSection("Breakfast", "Oatmeal with fruits and nuts", 450, Green),
            CaloriesSection("Lunch", "Chops with potatoes", 384, Yellow),
            CaloriesSection("Dinner", "No products added yet", 0, Orange),
            CaloriesSection("Other", "No products added yet", 0, Blue)
        )

        meals.forEachIndexed { index, (title, description, calories, color) ->
            MealCard(
                first = index == 0,
                title = title,
                description = description,
                calories = calories,
                color = color.lighten(0.2f),
                position = Position.fromIndex(index, meals.size)
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
    position : Position
) {

    val shape = getShapeFromPosition(position)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .height(100.dp)
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

        if(!first) {
            Canvas(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(50.dp) // Adjust size if needed
            ) {
                val trianglePath = Path().apply {
                    moveTo(size.width, 1f) // Start at the top-right corner
                    lineTo(size.width, (-70f)) // Bottom-right
                    quadraticTo(
                        size.width / 2 + 40 , size.height / 2 - 70, // Control point for the curve
                        0f, 0f // End at the bottom-left
                    )
                    close()
                }
                drawPath(
                    path = trianglePath,
                    color = color // Darker shade for decoration
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