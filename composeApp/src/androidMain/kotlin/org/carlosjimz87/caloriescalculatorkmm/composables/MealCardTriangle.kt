package org.carlosjimz87.caloriescalculatorkmm.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp
import org.carlosjimz87.caloriescalculatorkmm.utils.darken

@Composable
fun BoxScope.MealCardTriangle(backgroundColor: Color) {
    Canvas(
        modifier = Modifier
            .align(Alignment.TopEnd)
            .size(40.dp)
    ) {
        val trianglePath = Path().apply {
            moveTo(size.width, 0f) // Top-right corner
            lineTo(size.width, size.height) // Bottom-right
            quadraticBezierTo(0f, size.height / 2, 0f, size.height) // Curved hypotenuse
            close()
        }
        drawPath(
            path = trianglePath,
            color = backgroundColor.darken(0.2f) // Darker shade for decoration
        )
    }
}