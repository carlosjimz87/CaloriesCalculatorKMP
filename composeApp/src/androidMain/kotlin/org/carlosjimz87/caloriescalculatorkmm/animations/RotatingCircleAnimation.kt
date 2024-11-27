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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.carlosjimz87.caloriescalculatorkmm.R
import org.carlosjimz87.caloriescalculatorkmm.theme.Green
import org.carlosjimz87.caloriescalculatorkmm.utils.drawableToImageBitmap
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun RotatingCircleAnimation(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Green, // Default Green
    rotationDurationMillis: Int = 2000, // Time per full rotation
    bigDishSize : Float = 270f,
    smallDishSize : Float = 140f
) {
    val context = LocalContext.current
    val images = listOf(
        drawableToImageBitmap(context, R.drawable.mushrooms),
        drawableToImageBitmap(context, R.drawable.spaguettis),
        drawableToImageBitmap(context, R.drawable.salad),
        drawableToImageBitmap(context, R.drawable.squids)
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

            // Draw dish images
            drawDishImages(bigDishSize, smallDishSize, images, center, radius)
        }
    }
}

fun DrawScope.drawDishImages(
    bigDishSize: Float,
    smallDishSize: Float,
    images: List<ImageBitmap>,
    center: Offset,
    radius: Float
) {
    val quadrants = listOf(0f, 90f, 180f, 270f) // Angles for fixed quadrant positions
    val radiusFactor = 0.8f // Move the dishes further from the center
    val globalOffset = Offset(-30.dp.toPx(), -50.dp.toPx()) // Move everything up-left

    quadrants.forEachIndexed { index, angle ->
        val radians = Math.toRadians(angle.toDouble())
        val x = center.x + radius * radiusFactor * cos(radians).toFloat() + globalOffset.x
        val y = center.y + radius * radiusFactor * sin(radians).toFloat() + globalOffset.y

        // Set image size (big for even indices, small for odd indices)
        val imageSize = if (index % 2 == 0) bigDishSize.dp.toPx() else smallDishSize.dp.toPx()
        val scaleFactor = imageSize / images[index].width

        // Draw each dish image at a fixed quadrant position
        withTransform({
            translate(left = x - imageSize / 2, top = y - imageSize / 2) // Center the image
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