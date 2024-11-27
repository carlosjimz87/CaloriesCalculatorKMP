package org.carlosjimz87.caloriescalculatorkmm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import org.carlosjimz87.caloriescalculatorkmm.animations.RotatingCircleAnimation
import org.carlosjimz87.caloriescalculatorkmm.animations.RotatingDish
import org.carlosjimz87.caloriescalculatorkmm.animations.RotatingTableclothAnimation
import org.carlosjimz87.caloriescalculatorkmm.screens.RegisterScreen
import org.carlosjimz87.caloriescalculatorkmm.utils.drawableToImageBitmap

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RotatingTableclothAnimation()
        }
    }
}