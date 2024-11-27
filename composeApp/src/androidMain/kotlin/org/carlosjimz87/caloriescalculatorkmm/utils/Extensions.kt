package org.carlosjimz87.caloriescalculatorkmm.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import org.carlosjimz87.caloriescalculatorkmm.R
import kotlin.math.sqrt
import kotlin.random.Random


fun flagFromCountryCode(countryCode: String): Int {
    return when (countryCode) {
        "+1" -> R.drawable.ic_flag_us
        "+44"  -> R.drawable.ic_flag_uk
        "+91"  -> R.drawable.ic_flag_in
        else -> R.drawable.ic_flag_unknown
        }
    }


fun drawableToImageBitmap(context: Context, @DrawableRes resId: Int): ImageBitmap {
    val drawable = AppCompatResources.getDrawable(context, resId) ?: throw IllegalArgumentException("Drawable not found")
    val bitmap = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return bitmap.asImageBitmap()
}


fun DrawScope.drawImageOnCanvas(
    x: Float,
    imageSize: Float,
    y: Float,
    scaleFactor: Float,
    images: List<ImageBitmap>,
    index: Int,
    colorTint: Color?
) {
    withTransform({
        translate(left = x - imageSize / 2, top = y - imageSize / 2) // Center the image
        scale(scaleFactor, scaleFactor) // Scale the image
    }) {
        drawImage(
            images[index],
            colorFilter = colorTint?.let { ColorFilter.tint(it) },
        )
    }
}

/**
 * Calculates the size needed for the rotating tablecloth to cover the entire screen,
 * including during rotation.
 */
@Composable
fun calculateMaxRotationSize(): Dp {
    val screenWidthDp = LocalConfiguration.current.screenWidthDp.dp
    val screenHeightDp = LocalConfiguration.current.screenHeightDp.dp

    // Calculate the diagonal of the screen (largest possible dimension)
    val diagonalDp = sqrt(screenWidthDp.value * screenWidthDp.value + screenHeightDp.value * screenHeightDp.value)

    // Add a buffer to ensure full coverage during rotation
    return (diagonalDp * 1.8f).dp // Add 20% buffer for safety
}

fun ClosedFloatingPointRange<Float>.random(): Float =
    Random.nextFloat() * (endInclusive - start) + start

fun Dp.toPx(density: Density) = with(density) { this@toPx.toPx() }