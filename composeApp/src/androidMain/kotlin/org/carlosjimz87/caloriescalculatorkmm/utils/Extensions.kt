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
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import org.carlosjimz87.caloriescalculatorkmm.R


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