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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import org.carlosjimz87.caloriescalculatorkmm.R
import org.carlosjimz87.caloriescalculatorkmm.theme.Green
import org.carlosjimz87.caloriescalculatorkmm.theme.LightGreen
import org.carlosjimz87.caloriescalculatorkmm.utils.drawImageOnCanvas
import org.carlosjimz87.caloriescalculatorkmm.utils.drawableToImageBitmap
import org.carlosjimz87.caloriescalculatorkmm.utils.random
import org.carlosjimz87.caloriescalculatorkmm.utils.toPx
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun RotatingCircleAnimation(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Green, // Default Green
    rotationDurationMillis: Int = 2000, // Time for the circle to rotate
    bigDishSize: Float = 270f,
    smallDishSize: Float = 140f,
    silhouettesSize: Float = 100f
) {
    val context = LocalContext.current
    val density = LocalDensity.current

    // Load dish images
    val dishImages = listOf(
        drawableToImageBitmap(context, R.drawable.mushrooms),
        drawableToImageBitmap(context, R.drawable.spaguettis),
        drawableToImageBitmap(context, R.drawable.salad),
        drawableToImageBitmap(context, R.drawable.squids)
    )

    // Load silhouette images
    val silhouettes = listOf(
        drawableToImageBitmap(context, R.drawable.apple_sil),
        drawableToImageBitmap(context, R.drawable.bread_sil),
        drawableToImageBitmap(context, R.drawable.bread_sil),
        drawableToImageBitmap(context, R.drawable.fish_sil)
    )

    val infiniteRotation = rememberInfiniteTransition(label = "circleRotation")
    val circleRotationAngle by infiniteRotation.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = rotationDurationMillis, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "circleRotationAngle"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        // Rotating Content
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(
                    rotationZ = circleRotationAngle,
                    transformOrigin = TransformOrigin(0.5f, 0.5f)
                ), // Rotate entire circle
        ) {
            val radius = with(density) { 300.dp.toPx() } // Example radius
            val center = Offset(radius, radius) // Example center
            // Draw silhouettes using Canvas
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawSilhouettes(
                    images = silhouettes,
                    center = center,
                    radius = radius,
                    dishSize = bigDishSize,
                    silhouetteSize = silhouettesSize,
                    silhouetteColorTint = LightGreen
                )
            }

            // Draw dishes as composables
            val quadrants = listOf(0f, 90f, 180f, 270f) // Fixed quadrant angles
            val radiusFactor = 0.8f
            val globalOffset = Offset(-30.dp.toPx(density), -50.dp.toPx(density)) // Move up-left

            quadrants.forEachIndexed { index, angle ->
                val radians = Math.toRadians(angle.toDouble())
                val dishX = (radius * radiusFactor * cos(radians).toFloat()) + globalOffset.x
                val dishY = (radius * radiusFactor * sin(radians).toFloat()) + globalOffset.y

                val imageSize = if (index % 2 == 0) bigDishSize.dp else smallDishSize.dp

                RotatingDish(
                    modifier = Modifier
                        .offset(x = dishX.dp, y = dishY.dp)
                        .size(imageSize),
                    image = dishImages[index],
                    rotationDurationMillis = 2000 // Independent animation for each dish
                )
            }
        }
    }
}

@Composable
fun DrawScope.drawDishes(
    d: Density,
    images: List<ImageBitmap>,
    center: Offset,
    radius: Float,
    firstSize: Float,
    secondSize: Float? = null,
    colorTint : Color? = null
) {
    // Draw animated dishes
    val quadrants = listOf(0f, 90f, 180f, 270f) // Fixed quadrant angles
    val radiusFactor = 0.8f
    val globalOffset = Offset(-30.dp.toPx(d), -50.dp.toPx(d)) // Move up-left

    quadrants.forEachIndexed { index, angle ->
        val radians = Math.toRadians(angle.toDouble())
        val dishX = (radius * radiusFactor * cos(radians).toFloat()) + globalOffset.x
        val dishY = (radius * radiusFactor * sin(radians).toFloat()) + globalOffset.y

        val imageSize = secondSize?.let { if (index % 2 == 0) firstSize.dp else it.dp } ?: firstSize.dp

        RotatingDish(
            modifier = Modifier
                .offset(x = dishX.dp, y = dishY.dp)
                .size(imageSize),
            image = images[index],
            rotationDurationMillis = 2000 // Independent animation for each dish
        )
    }
}


fun DrawScope.drawSilhouettes(
    images: List<ImageBitmap>,
    center: Offset,
    radius: Float,
    dishSize: Float,
    silhouetteSize: Float,
    silhouetteColorTint: Color? = null
) {
    val quadrants = listOf(0f, 90f, 180f, 270f) // Fixed quadrant angles for dishes
    val radiusFactor = 0.8f // Same radius factor as the dishes
    val silhouetteOffset = dishSize / 2 + silhouetteSize / 2 + 20.dp.toPx() // Distance from the dish
    val randomSpread = 15.dp.toPx() // Small random spread for silhouettes

    quadrants.forEachIndexed { index, angle ->
        val radians = Math.toRadians(angle.toDouble())
        val dishX = center.x + radius * radiusFactor * cos(radians).toFloat()
        val dishY = center.y + radius * radiusFactor * sin(radians).toFloat()

        // Hardcoded positions for silhouettes around the dish
        val silhouettePositions = listOf(
            Offset(dishX - silhouetteOffset, dishY), // Left of the dish
            Offset(dishX + silhouetteOffset, dishY), // Right of the dish
            Offset(dishX, dishY - silhouetteOffset), // Above the dish
            Offset(dishX, dishY + silhouetteOffset)  // Below the dish
        )

        // Draw silhouettes at the calculated positions
        silhouettePositions.forEach { position ->
            val adjustedX = position.x + (-randomSpread..randomSpread).random()
            val adjustedY = position.y + (-randomSpread..randomSpread).random()
            drawImageOnCanvas(
                x = adjustedX,
                y = adjustedY,
                imageSize = silhouetteSize,
                scaleFactor = silhouetteSize / images[index % images.size].width,
                images = images,
                index = index % images.size,
                colorTint = silhouetteColorTint
            )
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