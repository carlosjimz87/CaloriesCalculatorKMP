package org.carlosjimz87.caloriescalculatorkmm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.carlosjimz87.caloriescalculatorkmm.animations.RotatingTableclothAnimation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RotatingTableclothAnimation()
        }
    }
}