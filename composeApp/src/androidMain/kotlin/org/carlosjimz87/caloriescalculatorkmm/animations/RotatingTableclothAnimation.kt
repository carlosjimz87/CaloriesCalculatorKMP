package org.carlosjimz87.caloriescalculatorkmm.animations

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
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
        R.drawable.mushrooms,
        R.drawable.squids,
        R.drawable.spaguettis,
        R.drawable.tomahawk
    ),
    dishesQuadrants: List<Float> = listOf(0f, 90f, 180f, 270f), // Angles for orbiting dishes
    dishSizes: List<Dp> = listOf(75.dp, 125.dp, 125.dp, 125.dp, 125.dp), // Sizes for each dish
    distanceFromCenter: Dp = 45.dp,
    tableRotationDurationMillis: Int = 800,
    dishRotationDurationMillis: Int = 800,
    tableRotationAngleValue: Float = 90f,
    dishesRotationAngleValue: Float = 180f,
    easing: Easing = CubicBezierEasing(0.42f, 0f, 0.58f, 1f),
    fractionOfProgress: Float = 0.8f,
    xOffset: Dp = 50.dp,
    yOffset: Dp = -(70).dp,
    enable: Boolean = true,
    reverse: Boolean = false,
) {
    require(dishesImagesResources.size == dishSizes.size) {
        "The number of dishes and sizes must match"
    }

    val context = LocalContext.current
    val density = LocalDensity.current

    val dishesBitmaps = remember(dishesImagesResources) {
        dishesImagesResources.map { drawableToImageBitmap(context, it) }
    }

    val containerRotationAngle = remember { Animatable(0f) }
    val dishRotationAngles = remember {
        List(dishesImagesResources.size - 1) { Animatable(0f) }
    }

    LaunchedEffect(enable) {
        if (enable) {
            coroutineScope {
                launch {
                    val tableTargetAngle = if (reverse) -tableRotationAngleValue else tableRotationAngleValue
                    containerRotationAngle.animateTo(
                        targetValue = tableTargetAngle,
                        animationSpec = tween(
                            durationMillis = tableRotationDurationMillis,
                            easing = easing
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
                                easing = easing
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
                scaleX = 3f,
                scaleY = 3f,
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

        val dishPositions = remember(dishesQuadrants) {
            dishesQuadrants.map { angle ->
                val radians = Math.toRadians(angle.toDouble() + containerRotationAngle.value)
                Offset(
                    x = distanceFromCenter.toPx(density) * cos(radians).toFloat(),
                    y = distanceFromCenter.toPx(density) * sin(radians).toFloat()
                )
            }
        }

        // Orbiting Dishes
        dishPositions.forEachIndexed { index, pos ->
            val adjustedIndex = index + 1

            Dish(
                image = dishesBitmaps[adjustedIndex],
                size = dishSizes[adjustedIndex],
                zRotation = dishRotationAngles[index].value,
                x = pos.x.dp,
                y = pos.y.dp
            )
            println("Dish -> $adjustedIndex at $pos")
        }
    }
}

@Preview
@Composable
private fun RotatingTableclothAnimationPreview() {
    RotatingTableclothAnimation()
}