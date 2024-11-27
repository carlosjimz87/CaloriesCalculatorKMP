package org.carlosjimz87.caloriescalculatorkmm.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import org.carlosjimz87.caloriescalculatorkmm.R


fun flagFromCountryCode(countryCode: String): Int {
    return when (countryCode) {
        "+1" -> R.drawable.ic_flag_us
        "+44"  -> R.drawable.ic_flag_uk
        "+91"  -> R.drawable.ic_flag_in
        else -> R.drawable.ic_flag_unknown
        }
    }


@Composable
fun Painter.toBitmap(): ImageBitmap {
    return (this as? BitmapPainter)?.toBitmap()
        ?: throw IllegalArgumentException("Unable to convert painter to ImageBitmap")
}