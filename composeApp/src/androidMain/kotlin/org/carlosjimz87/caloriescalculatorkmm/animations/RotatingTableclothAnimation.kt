package org.carlosjimz87.caloriescalculatorkmm.animations

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.carlosjimz87.caloriescalculatorkmm.R
import org.carlosjimz87.caloriescalculatorkmm.theme.Green
import org.carlosjimz87.caloriescalculatorkmm.utils.calculateMaxRotationSize
import org.carlosjimz87.caloriescalculatorkmm.utils.drawableToImageBitmap
import org.carlosjimz87.caloriescalculatorkmm.utils.toPx
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun RotatingTableclothAnimation(
    modifier: Modifier = Modifier,
    dishesImagesResources: List<Int> = listOf(
        R.drawable.mushrooms,
        R.drawable.spaguettis,
        R.drawable.salad
    ),
    dishesQuadrants: List<Float> = listOf(0f, 90f, 180f), // Angles for visible quadrants
    backgroundColor: Color = Green, // Default tablecloth color
    distanceFromCenter: Dp = 60.dp, // Distance from center to place the dishes
    tableRotationDurationMillis: Int = 1000, // Time for the tablecloth rotation
    dishRotationDurationMillis: Int = 1000, // Time for dish rotation
    tableRotationAngleValue: Float = 90f, // Angle for the tablecloth rotation
    dishesRotationAngleValue: Float = 220f, // Angles for the dishes rotation
    dishSize: Dp = 185.dp, // Size of each dish
    mainEasing: Easing = CubicBezierEasing(0.42f, 0.0f, 0.58f, 1.0f), // Smooth easing
    secondaryEasing: Easing = CubicBezierEasing(0.17f, 0.89f, 0.32f, 1.28f), // Smooth easing
    fractionOfProgress: Float = 0.9f, // Fraction of the main animation when dishes start
    enable: Boolean = true, // Enable/Disable animation
    reverse: Boolean = false // Clockwise or counterclockwise
) {
    if (dishesImagesResources.size != dishesQuadrants.size) {
        throw IllegalArgumentException("The number of dishes must match the number of quadrants")
    }

    val context = LocalContext.current
    val density = LocalDensity.current

    val dishesBitmaps = dishesImagesResources.map {
        drawableToImageBitmap(context, it)
    }

    // State for the table rotation
    val containerRotationAngle = remember { Animatable(0f) }
    // States for the individual dish rotations
    val dishRotationAngles = List(dishesImagesResources.size) { remember { Animatable(0f) } }

    LaunchedEffect(enable) {
        if (enable) {
            coroutineScope {
                launch {
                    // Adjust direction based on `reversible`
                    val tableTargetAngle = if (reverse) -tableRotationAngleValue else tableRotationAngleValue
                    containerRotationAngle.animateTo(
                        targetValue = tableTargetAngle,
                        animationSpec = tween(
                            durationMillis = tableRotationDurationMillis,
                            easing = mainEasing
                        )
                    )
                }
                dishRotationAngles.forEach { animatable ->
                    launch {
                        // Adjust direction based on `reversible`
                        val dishTargetAngle = if (reverse) -dishesRotationAngleValue else dishesRotationAngleValue
                        animatable.animateTo(
                            targetValue = dishTargetAngle,
                            animationSpec = tween(
                                durationMillis = dishRotationDurationMillis,
                                delayMillis = (tableRotationDurationMillis * fractionOfProgress).toInt(),
                                easing = secondaryEasing
                            )
                        )
                    }
                }
            }
        } else {
            // Reset animations to initial state when disabled
            containerRotationAngle.snapTo(0f)
            dishRotationAngles.forEach { it.snapTo(0f) }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .graphicsLayer(rotationZ = containerRotationAngle.value) // Rotate entire container
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        // Tablecloth (fills the entire container, no need for extra size calculations)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .size(calculateMaxRotationSize())
                .paint(
                    painterResource(id = R.drawable.background),
                    contentScale = ContentScale.Crop
                )
        )

        // Dishes (visible quadrants)
        dishesQuadrants.forEachIndexed { index, angle ->
            // Calculate position of the dish
            val combinedAngle = angle + containerRotationAngle.value
            val radians = Math.toRadians(combinedAngle.toDouble())
            val radius = distanceFromCenter.toPx(density) // Distance from center
            val dishX = radius * cos(radians).toFloat()
            val dishY = radius * sin(radians).toFloat()

            // Draw dish
            Box(
                modifier = Modifier
                    .offset(x = dishX.dp, y = dishY.dp)
                    .size(dishSize)
                    .graphicsLayer(rotationZ = dishRotationAngles[index].value),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    bitmap = dishesBitmaps[index],
                    contentDescription = "Rotating Dish",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}