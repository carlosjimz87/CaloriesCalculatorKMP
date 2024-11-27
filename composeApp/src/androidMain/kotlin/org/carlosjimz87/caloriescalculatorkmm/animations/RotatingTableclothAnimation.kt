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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.carlosjimz87.caloriescalculatorkmm.R
import org.carlosjimz87.caloriescalculatorkmm.theme.Green
import org.carlosjimz87.caloriescalculatorkmm.theme.White
import org.carlosjimz87.caloriescalculatorkmm.utils.drawableToImageBitmap
import org.carlosjimz87.caloriescalculatorkmm.utils.toPx
import kotlin.math.cos
import kotlin.math.sin


@Composable
fun RotatingTableclothAnimation(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Green, // Default tablecloth color
    tableRotationDurationMillis: Int = 1000, // Time for the tablecloth rotation
    dishRotationDurationMillis: Int = 1500, // Time for dish rotation
    tableRotationAngleValue: Float = 90f, // Angle for the tablecloth rotation
    dishesRotationAngleValue: Float = 180f, // Angles for the dishes rotation
    dishSize: Dp = 140.dp, // Size of each dish
    mainEasing: Easing = CubicBezierEasing(0.42f, 0.0f, 0.58f, 1.0f), // Smooth easing
    secondaryEasing: Easing = CubicBezierEasing(0.17f, 0.89f, 0.32f, 1.28f), // Smooth easing
    fractionOfProgress: Float = 0.9f // Fraction of the main animation when dishes start
) {
    val context = LocalContext.current

    // State for the table rotation
    val tableRotationAngle = remember { Animatable(0f) }
    // States for the individual dish rotations
    val dishRotationAngles = List(3) { remember { Animatable(0f) } }

    LaunchedEffect(Unit) {
        // Start both animations simultaneously
        coroutineScope {
            launch {
                tableRotationAngle.animateTo(
                    targetValue = tableRotationAngleValue,
                    animationSpec = tween(
                        durationMillis = tableRotationDurationMillis,
                        easing = mainEasing
                    )
                )
            }
            dishRotationAngles.forEach { animatable ->
                launch {
                    // Dish animation starts at a specific fraction of the tablecloth's progress
                    animatable.animateTo(
                        targetValue = dishesRotationAngleValue,
                        animationSpec = tween(
                            durationMillis = dishRotationDurationMillis,
                            delayMillis = (tableRotationDurationMillis * fractionOfProgress).toInt(),
                            easing = secondaryEasing
                        )
                    )
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer(rotationZ = tableRotationAngle.value)
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        // Dishes (visible quadrants)
        val quadrantAngles = listOf(45f, 135f, 225f) // Angles for visible quadrants
        quadrantAngles.forEachIndexed { index, angle ->
            // Calculate position of the dish
            val radians = Math.toRadians(angle.toDouble())
            val radius = 80.dp.toPx(LocalDensity.current) // Distance from center
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
                    bitmap = drawableToImageBitmap(context, R.drawable.mushrooms),
                    contentDescription = "Rotating Dish",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}