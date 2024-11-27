package org.carlosjimz87.caloriescalculatorkmm.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.IntSize
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