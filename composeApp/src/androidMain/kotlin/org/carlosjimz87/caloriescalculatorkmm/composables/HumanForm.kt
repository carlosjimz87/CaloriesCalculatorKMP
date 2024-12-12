package org.carlosjimz87.caloriescalculatorkmm.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.carlosjimz87.caloriescalculatorkmm.theme.Green
import org.carlosjimz87.caloriescalculatorkmm.theme.LightGray
import org.carlosjimz87.caloriescalculatorkmm.theme.Yellow
import androidx.compose.ui.graphics.Color
import org.carlosjimz87.caloriescalculatorkmm.theme.LightGreen
import org.carlosjimz87.caloriescalculatorkmm.theme.LightYellow

@Composable
fun HumanFormWithThreshold(modifier: Modifier = Modifier, thresholdFraction: Float = 0.5f) {
    val formPoints = remember { generateHumanFormPoints() } // Generate points in normalized space
    val thresholdY by remember { mutableFloatStateOf(thresholdFraction) } // Normalized threshold

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize() // Use all available space
                .background(LightGray)
        ) {
            Canvas(modifier = Modifier.fillMaxSize().padding(32.dp)) {
                val canvasWidth = size.width
                val canvasHeight = size.height
                val thresholdYPosition = thresholdY * canvasHeight

                // Draw the dots
                formPoints.forEach { point ->
                    val absoluteX = point.x * canvasWidth
                    val absoluteY = point.y * canvasHeight

                    drawCircle(
                        color = if (absoluteY < thresholdYPosition) Green else Yellow,
                        radius = 4f,
                        center = Offset(absoluteX, absoluteY)
                    )
                }
                val linesPositions = generateLinesPositionWithSeparation(thresholdYPosition)

                // Draw the threshold line
                linesPositions.forEach {
                     drawLine(
                        color = it.first,
                        start = Offset(0f, it.second),
                        end = Offset(canvasWidth, it.second),
                        strokeWidth = it.third
                    )
                }
                drawLine(
                    color = Green,
                    start = Offset(0f, thresholdYPosition),
                    end = Offset(canvasWidth, thresholdYPosition),
                    strokeWidth = 8f
                )
            }
        }
    }
}

fun generateLinesPositionWithSeparation(
    baseY: Float = 1000f,
    separation: Float = 40f,
    stroke: Float = 4f,
    count: Int = 10 // Number of lines above and below
): List<Triple<Color, Float, Float>> {
    return (1..count).flatMap { index ->
        listOf(
            // Lines above the baseY
            Triple(LightGreen, baseY - index * separation, stroke),
            // Lines below the baseY
            Triple(LightYellow, baseY + index * separation, stroke)
        )
    }
}

fun generateHumanFormPoints(): List<Offset> {
    return listOf(
        // Head (circle approximation)
        Offset(0.5f, 0.1f), Offset(0.49f, 0.09f), Offset(0.51f, 0.09f),
        Offset(0.48f, 0.11f), Offset(0.52f, 0.11f), Offset(0.47f, 0.12f),
        Offset(0.53f, 0.12f), Offset(0.48f, 0.08f), Offset(0.52f, 0.08f),
        Offset(0.5f, 0.13f),

        // Torso (vertical alignment)
        Offset(0.5f, 0.15f), Offset(0.49f, 0.18f), Offset(0.51f, 0.18f),
        Offset(0.48f, 0.2f), Offset(0.52f, 0.2f), Offset(0.47f, 0.25f),
        Offset(0.53f, 0.25f), Offset(0.46f, 0.3f), Offset(0.54f, 0.3f),
        Offset(0.5f, 0.35f), Offset(0.49f, 0.4f), Offset(0.51f, 0.4f),

        // Left Arm (curved outward)
        Offset(0.45f, 0.2f), Offset(0.44f, 0.23f), Offset(0.43f, 0.26f),
        Offset(0.42f, 0.3f), Offset(0.41f, 0.33f), Offset(0.4f, 0.35f),

        // Right Arm (curved outward)
        Offset(0.55f, 0.2f), Offset(0.56f, 0.23f), Offset(0.57f, 0.26f),
        Offset(0.58f, 0.3f), Offset(0.59f, 0.33f), Offset(0.6f, 0.35f),

        // Left Leg (slightly outward)
        Offset(0.48f, 0.42f), Offset(0.47f, 0.45f), Offset(0.46f, 0.5f),
        Offset(0.45f, 0.55f), Offset(0.44f, 0.6f), Offset(0.43f, 0.65f),

        // Right Leg (slightly outward)
        Offset(0.52f, 0.42f), Offset(0.53f, 0.45f), Offset(0.54f, 0.5f),
        Offset(0.55f, 0.55f), Offset(0.56f, 0.6f), Offset(0.57f, 0.65f)
    )
}

@Preview
@Composable
private fun HumanFormWithThresholdPreview() {
    HumanFormWithThreshold()
}