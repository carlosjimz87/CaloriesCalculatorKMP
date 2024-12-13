package org.carlosjimz87.caloriescalculatorkmm.composables

import androidx.compose.foundation.Canvas
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.carlosjimz87.caloriescalculatorkmm.Constants
import org.carlosjimz87.caloriescalculatorkmm.theme.Green
import org.carlosjimz87.caloriescalculatorkmm.theme.LightGreen
import org.carlosjimz87.caloriescalculatorkmm.theme.LightYellow
import org.carlosjimz87.caloriescalculatorkmm.theme.Yellow
import kotlin.math.abs

@Composable
fun HumanFormWithThreshold(modifier: Modifier = Modifier, thresholdFraction: Float = 0.5f) {
    // Threshold in normalized space
    val thresholdY by remember { mutableFloatStateOf(thresholdFraction) }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            HumanFormCanvasLayer(thresholdY = thresholdY, modifier = Modifier
                .fillMaxSize()
                .padding(8.dp))
        }
    }
}

@Composable
fun HumanFormCanvasLayer(thresholdY: Float, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val thresholdYPosition = thresholdY * canvasHeight

        // Generate absolute points for the human form
        val absolutePoints = generateHumanFormPoints(canvasWidth, canvasHeight)

        // Draw the human form points
        absolutePoints.forEach { point ->
            drawCircle(
                color = if (point.y < thresholdYPosition) Green else Yellow,
                radius = 4f,
                center = point
            )
        }

        // Generate and draw lines above and below the threshold
        val linesPositions = generateLinesPositionWithSeparation(thresholdYPosition)
        linesPositions.forEach { (yPosition, color, stroke) ->
            drawLine(
                color = color,
                start = Offset(0f, yPosition),
                end = Offset(canvasWidth, yPosition),
                strokeWidth = stroke
            )
        }

        // Draw the main threshold line
        drawLine(
            color = Green,
            start = Offset(0f, thresholdYPosition),
            end = Offset(canvasWidth, thresholdYPosition),
            strokeWidth = 8f
        )
    }
}

fun generateHumanFormPoints(
    canvasWidth: Float,
    canvasHeight: Float,
    baseY: Float = 0.5f,
    heightPercentage: Float = 0.8f
): List<Offset> {
    // Generate normalized points and scale to canvas size
    return Constants.humanFormPoints.map { point ->
        // Adjust Y-coordinate for base position and scale
        val scaledY = ((point.y - 0.5f) * heightPercentage) + baseY
        Offset(point.x * canvasWidth, scaledY * canvasHeight)
    }
}

fun generateLinesPositionWithSeparation(
    baseY: Float = 1000f,
    separation: Float = 40f,
    count: Int = 15,
): List<Triple<Float, Color, Float>> {

    return (-count..count).map { index ->
        val yPosition = baseY + index * separation
        val color = if (index < 0) LightGreen else LightYellow

        // Calculate alpha with a quadratic falloff
        val distanceFromCenter = abs(index) * separation
        val normalizedDistance = (distanceFromCenter / (count * separation)).coerceIn(0f, 1f)
        val alpha = (1f - normalizedDistance * normalizedDistance).coerceIn(0f, 1f)

        Triple(yPosition, color.copy(alpha = alpha), 4f) // Include y-position, color with alpha, and stroke width
    }
}

@Preview(showBackground = true)
@Composable
private fun HumanFormWithThresholdPreview() {
    HumanFormWithThreshold()
}