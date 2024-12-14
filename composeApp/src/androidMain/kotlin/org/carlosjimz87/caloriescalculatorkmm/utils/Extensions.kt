package org.carlosjimz87.caloriescalculatorkmm.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.carlosjimz87.caloriescalculatorkmm.Constants
import org.carlosjimz87.caloriescalculatorkmm.R
import org.carlosjimz87.caloriescalculatorkmm.models.Position
import org.carlosjimz87.caloriescalculatorkmm.theme.LightGreen
import org.carlosjimz87.caloriescalculatorkmm.theme.LightYellow
import kotlin.math.abs


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

fun Dp.toPx(density: Density) = with(density) { this@toPx.toPx() }


/**
 * Extension function to open a URL in a web browser.
 *
 * @param url The URL to open.
 */
fun openInBrowser(context: Context, url: String) {
    with(context){

        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

            // Get a list of apps that can handle this intent
            val resolveInfoList = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)

            if (resolveInfoList.isNotEmpty()) {
                // Launch the intent if there's at least one app available
                startActivity(intent)
            } else {
                Toast.makeText(this, "No application available to open this URL", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Failed to open URL: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}

// Extension for Int to convert to percent
val Int.percent: Dp
    get() = (this * 0.01).dp

// Extension to darken a color
fun Color.darken(factor: Float): Color {
    return copy(
        red = red * factor,
        green = green * factor,
        blue = blue * factor
    )
}

fun Color.lighten(factor: Float): Color {
    return copy(
        red = red + (1 - red) * factor,
        green = green + (1 - green) * factor,
        blue = blue + (1 - blue) * factor
    )
}


fun getShapeFromPosition(position: Position): RoundedCornerShape {

    return when (position) {
        Position.TOP -> {
            RoundedCornerShape(
                topStartPercent = 40,
                topEndPercent = 10,
                bottomStartPercent = 0,
                bottomEndPercent = 40
            )
        }
        Position.MIDDLE -> {
            RoundedCornerShape(
                topStartPercent = 0,
                topEndPercent = 0,
                bottomStartPercent = 0,
                bottomEndPercent = 40
            )
        }
        Position.BOTTOM -> {
            RoundedCornerShape(
                topStartPercent = 0,
                topEndPercent = 0,
                bottomStartPercent = 40,
                bottomEndPercent = 40
            )
        }
    }
}


data class HumanFormPoints(
    val canvasWidth: Float = 1000f,
    val canvasHeight: Float = 1000f,
    val baseY: Float = 0.5f,
    val heightPercentage: Float = 0.8f
) {
    fun create(): List<Offset> {
        // Generate normalized points and scale to canvas size
        return Constants.humanFormPoints.map { point ->
            // Adjust Y-coordinate for base position and scale
            val scaledY = ((point.y - 0.5f) * heightPercentage) + baseY
            Offset(point.x * canvasWidth, scaledY * canvasHeight)
        }
    }
}

data class LinesPositionWithSeparation(
    val baseY: Float = 1000f,
    val separation: Float = 40f,
    val count: Int = 10,
) {
    fun create(): List<Triple<Float, Color, Float>> {
        return (-count..count).map { index ->
            val yPosition = baseY + index * separation
            val color = if (index < 0) LightGreen else LightYellow

            // Calculate alpha with a quadratic falloff
            val distanceFromCenter = abs(index) * separation
            val normalizedDistance = (distanceFromCenter / (count * separation)).coerceIn(0f, 1f)
            val alpha = (1f - normalizedDistance * normalizedDistance).coerceIn(0f, 1f)

            Triple(yPosition, color.copy(alpha = alpha), 4f) // Include y-position, color with alpha, and stroke width
        }
    }
}