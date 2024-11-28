package org.carlosjimz87.caloriescalculatorkmm.animations

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.carlosjimz87.caloriescalculatorkmm.R
import org.carlosjimz87.caloriescalculatorkmm.composables.Dish
import org.carlosjimz87.caloriescalculatorkmm.utils.drawableToImageBitmap
import org.carlosjimz87.caloriescalculatorkmm.utils.toPx
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun RotatingTableclothAnimation(
    modifier: Modifier = Modifier,
    dishesImagesResources: List<Int> = listOf(
        R.drawable.salad,
        R.drawable.spaguettis,
        R.drawable.squids,
        R.drawable.mushrooms,
        R.drawable.tomahawk
    ),
    dishesQuadrants: List<Float> = listOf(0f, 90f, 180f, 270f), // Angles for orbiting dishes
    dishSizes: List<Dp> = listOf(75.dp, 125.dp, 125.dp, 125.dp, 125.dp), // Sizes for each dish
    distanceFromCenter: Dp = 50.dp, // Distance from center to place orbiting dishes
    tableRotationDurationMillis: Int = 1000, // Time for the tablecloth rotation
    dishRotationDurationMillis: Int = 1000, // Time for dish rotation
    tableRotationAngleValue: Float = 90f, // Angle for the tablecloth rotation
    dishesRotationAngleValue: Float = 180f, // Angles for the dishes rotation
    mainEasing: Easing = CubicBezierEasing(0.42f, 0.0f, 0.58f, 1.0f), // Smooth easing
    secondaryEasing: Easing = CubicBezierEasing(0.17f, 0.89f, 0.32f, 1.28f), // Smooth easing
    fractionOfProgress: Float = 0.9f, // Fraction of the main animation when dishes start
    enable: Boolean = true, // Enable/Disable animation
    reverse: Boolean = false, // Clockwise or counterclockwise
    xOffset: Dp = 60.dp, // Horizontal translation for the entire system
    yOffset: Dp = (-80).dp  // Vertical translation for the entire system
) {
    require(dishesImagesResources.size == dishSizes.size) {
        "The number of dishes and sizes must match"
    }

    val context = LocalContext.current
    val density = LocalDensity.current

    val dishesBitmaps = dishesImagesResources.map {
        drawableToImageBitmap(context, it)
    }

    val containerRotationAngle = remember { Animatable(0f) }
    val dishRotationAngles = List(dishesImagesResources.size - 1) { remember { Animatable(0f) } }

    LaunchedEffect(enable) {
        if (enable) {
            coroutineScope {
                launch {
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
            containerRotationAngle.snapTo(0f)
            dishRotationAngles.forEach { it.snapTo(0f) }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .graphicsLayer(
                rotationZ = containerRotationAngle.value,
                scaleX = 2.5f,
                scaleY = 2.5f
            )
            .offset(x = xOffset, y = yOffset)
            .paint(
                painterResource(id = R.drawable.background),
                contentScale = ContentScale.Fit
            ),
        contentAlignment = Alignment.Center
    ) {
        // Center Dish
        Dish(
            image = dishesBitmaps[0],
            size = dishSizes[0],
            zRotation = dishRotationAngles[0].value,
        )

        // Orbiting Dishes
        dishesQuadrants.forEachIndexed { index, angle ->
            val adjustedIndex = index + 1
            val radians = Math.toRadians((angle + containerRotationAngle.value).toDouble())
            val radius = distanceFromCenter.toPx(density)
            val dishX = radius * cos(radians).toFloat()
            val dishY = radius * sin(radians).toFloat()

            Dish(
                image = dishesBitmaps[adjustedIndex],
                size = dishSizes[adjustedIndex],
                zRotation = dishRotationAngles[index].value,
                x = dishX.dp,
                y = dishY.dp
            )
        }
    }
}

@Preview
@Composable
private fun RotatingTableclothAnimationPreview() {
    RotatingTableclothAnimation()
}