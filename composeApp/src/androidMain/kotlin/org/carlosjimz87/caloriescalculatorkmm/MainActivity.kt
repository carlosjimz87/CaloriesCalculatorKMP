package org.carlosjimz87.caloriescalculatorkmm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import org.carlosjimz87.caloriescalculatorkmm.animations.RotatingCircleAnimation
import org.carlosjimz87.caloriescalculatorkmm.animations.RotatingDish
import org.carlosjimz87.caloriescalculatorkmm.screens.RegisterScreen
import org.carlosjimz87.caloriescalculatorkmm.utils.drawableToImageBitmap

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val context = LocalContext.current
            val dishImage = drawableToImageBitmap(context, R.drawable.salad) // Replace with your dish drawable

            RotatingDish(
                image = dishImage,
                size = 150.dp, // Dish size
                rotationDurationMillis = 1000 // Rotate 90 degrees every 2 seconds
            )
        }
    }
}