package org.carlosjimz87.caloriescalculatorkmm.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import org.carlosjimz87.caloriescalculatorkmm.theme.Green
import org.carlosjimz87.caloriescalculatorkmm.theme.Yellow
import org.carlosjimz87.caloriescalculatorkmm.utils.HumanFormPoints
import org.carlosjimz87.caloriescalculatorkmm.utils.LinesPositionWithSeparation

@Composable
fun HumanFormWithThreshold(
    modifier: Modifier = Modifier,
    thresholdFraction: Float = 0.5f
) {
    val thresholdY by remember { mutableFloatStateOf(thresholdFraction) }

    ProportionalHeightWrapper(
        humanFormPercentage = 0.6f, // Adjust this value to control the height percentage
        modifier = modifier
    ) {
        HumanFormCanvasLayer(
            thresholdY = thresholdY,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun ProportionalHeightWrapper(
    humanFormPercentage: Float,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    require(humanFormPercentage in 0f..1f) { "humanFormPercentage must be between 0 and 1" }

    val spacerWeight = (1f - humanFormPercentage) / 2f

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(spacerWeight))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(humanFormPercentage)
        ) {
            content()
        }

        Spacer(modifier = Modifier.weight(spacerWeight))
    }
}


@Composable
fun HumanFormCanvasLayer(thresholdY: Float, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val thresholdYPosition = thresholdY * canvasHeight

        // Generate absolute points for the human form
        val absolutePoints = HumanFormPoints(canvasWidth, canvasHeight).create()

        // Draw the human form points
        absolutePoints.forEach { point ->
            drawCircle(
                color = if (point.y < thresholdYPosition) Green else Yellow,
                radius = 4f,
                center = point
            )
        }

        // Generate and draw lines above and below the threshold
        val linesPositions = LinesPositionWithSeparation(thresholdYPosition).create()
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

@Preview(showBackground = true)
@Composable
private fun HumanFormWithThresholdPreview() {
    HumanFormWithThreshold()
}