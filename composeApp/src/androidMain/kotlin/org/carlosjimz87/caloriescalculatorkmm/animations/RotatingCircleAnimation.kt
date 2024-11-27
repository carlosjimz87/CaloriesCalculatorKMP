package org.carlosjimz87.caloriescalculatorkmm.animations

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.carlosjimz87.caloriescalculatorkmm.R
import org.carlosjimz87.caloriescalculatorkmm.utils.toBitmap
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun RotatingCircleAnimation(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color(0xFFBFFFD1), // Default Green
    rotationDurationMillis: Int = 2000 // Time per full rotation
) {

    val images = listOf(
        painterResource(id = R.drawable.mushrooms).toBitmap(),
        painterResource(id = R.drawable.spaguettis).toBitmap(),
        painterResource(id = R.drawable.salad).toBitmap(),
        painterResource(id = R.drawable.squids).toBitmap()
    )

    val infiniteTransition = rememberInfiniteTransition(label = "infiniteRotation")
    val rotationAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = rotationDurationMillis, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "floatRotation"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor), // Background
        contentAlignment = Alignment.Center
    ) {
        // Rotating Content
        Canvas(
            modifier = Modifier
                .size(300.dp) // Circle size
                .graphicsLayer(rotationZ = rotationAngle) // Rotation animation
        ) {
            val radius = size.minDimension / 2f
            val center = Offset(size.width / 2, size.height / 2)

            // Draw silhouettes (placeholders between dish images)
            drawSilhouettes(center, radius)

            // Draw dish images
            drawDishImages(images, center, radius)
        }
    }
}

fun DrawScope.drawSilhouettes(center: Offset, radius: Float) {
    val silhouetteColor = Color(0xFFD4E157) // Lighter Green
    val positions = listOf(45f, 135f, 225f, 315f) // Angles for silhouettes

    positions.forEach { angle ->
        val radians = Math.toRadians(angle.toDouble())
        val x = center.x + radius * 0.7f * cos(radians).toFloat()
        val y = center.y + radius * 0.7f * sin(radians).toFloat()

        drawCircle(
            color = silhouetteColor,
            radius = 20.dp.toPx(),
            center = Offset(x, y)
        )
    }
}

fun DrawScope.drawDishImages(images: List<ImageBitmap>, center: Offset, radius: Float) {
    // Load the images as ImageBitmap

    val quadrants = listOf(0f, 90f, 180f, 270f) // Angles for dish images

    quadrants.forEachIndexed { index, angle ->
        val radians = Math.toRadians(angle.toDouble())
        val x = center.x + radius * 0.9f * cos(radians).toFloat()
        val y = center.y + radius * 0.9f * sin(radians).toFloat()

        // Set image size (big for even indices, small for odd indices)
        val imageSize = if (index % 2 == 0) 50.dp.toPx() else 25.dp.toPx()
        val scaleFactor = imageSize / images[index].width

        // Scale the image before drawing
        withTransform({
            translate(left = x - imageSize / 2, top = y - imageSize / 2) // Positioning
            scale(scaleFactor, scaleFactor) // Scale the image
        }) {
            drawImage(images[index])
        }
    }
}

@Preview(showBackground = true, apiLevel = 34)
@Composable
fun RotatingCircleAnimationPreview() {
    RotatingCircleAnimation(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // Add preview-specific background
    )
}