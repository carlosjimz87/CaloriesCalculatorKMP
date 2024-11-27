package org.carlosjimz87.caloriescalculatorkmm.animations

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.carlosjimz87.caloriescalculatorkmm.R
import org.carlosjimz87.caloriescalculatorkmm.utils.drawableToImageBitmap


@Composable
fun RotatingDish(
    modifier: Modifier = Modifier,
    image: ImageBitmap, // The dish image
    size: Dp = 150.dp, // Size of the dish
    degrees: Float = 180f,
    rotationDurationMillis: Int = 2000,
    easingType : Easing = CubicBezierEasing(0.17f, 0.89f, 0.32f, 1.28f),
    onAnimationEnd: (() -> Unit)? = null, // Optional callback when animation ends
) {
    // Animation state to control the rotation
    val rotationAngle = remember { Animatable(0f) }

    // Launch the rotation animation
    LaunchedEffect(key1 = rotationAngle) {
        rotationAngle.animateTo(
            targetValue = degrees,
            animationSpec = tween(durationMillis = rotationDurationMillis, easing = easingType)
        )
        // Invoke the callback if provided
        onAnimationEnd?.invoke()
    }

    Box(
        modifier = modifier
            .size(size)
            .graphicsLayer(rotationZ = rotationAngle.value), // Apply rotation
        contentAlignment = Alignment.Center
    ) {
        // Dish Image
        Image(
            bitmap = image,
            contentDescription = "Rotating Dish",
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
@Preview(showBackground = true, apiLevel = 34)
fun RotatingDishPreview() {
    val context = LocalContext.current
    val dishImage = drawableToImageBitmap(context, R.drawable.salad) // Replace with your dish drawable

    RotatingDish(
        image = dishImage,
        size = 150.dp, // Dish size
        rotationDurationMillis = 2000 // Rotate 90 degrees every 2 seconds
    )
}