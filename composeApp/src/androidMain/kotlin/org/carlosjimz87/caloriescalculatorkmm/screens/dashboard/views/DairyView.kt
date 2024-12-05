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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import org.carlosjimz87.caloriescalculatorkmm.theme.Blue
import org.carlosjimz87.caloriescalculatorkmm.theme.DarkGreen
import org.carlosjimz87.caloriescalculatorkmm.theme.Gray
import org.carlosjimz87.caloriescalculatorkmm.theme.Green
import org.carlosjimz87.caloriescalculatorkmm.theme.Orange
import org.carlosjimz87.caloriescalculatorkmm.theme.White
import org.carlosjimz87.caloriescalculatorkmm.theme.Yellow
import org.carlosjimz87.caloriescalculatorkmm.utils.darken

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
        val meals = listOf(
            CaloriesSection("Breakfast", "Oatmeal with fruits and nuts", 450, Green),
            CaloriesSection("Lunch", "Chops with potatoes", 384, Yellow),
            CaloriesSection("Dinner", "No products added yet", 0, Orange),
            CaloriesSection("Other", "No products added yet", 0, Blue)
        )

        meals.forEach { (title, description, calories, color) ->
            MealCard(
                title = title,
                description = description,
                calories = calories,
                color = color
            )
        }
    }
}

@Composable
fun MealCard(
    title: String,
    description: String,
    calories: Int,
    color: Color
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .height(100.dp)
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = color),
            shape = RoundedCornerShape(
                topStartPercent = 20,
                topEndPercent = 6,
                bottomStartPercent = 0,
                bottomEndPercent = 50
            ),
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
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = DarkGreen
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

//        Canvas(
//            modifier = Modifier
//                .align(Alignment.TopEnd)
//                .size(40.dp)
//        ) {
//            val trianglePath = Path().apply {
//                moveTo(size.width, 0f) // Top-right corner
//                lineTo(size.width, size.height) // Bottom-right
//                quadraticBezierTo(0f, size.height / 2, 0f, size.height) // Curved hypotenuse
//                close()
//            }
//            drawPath(
//                path = trianglePath,
//                color = color.darken(0.2f) // Darker shade for decoration
//            )
//        }
    }

}

@Preview(showBackground = true)
@Composable
fun DiaryViewPreview() {
    DiaryView()
}