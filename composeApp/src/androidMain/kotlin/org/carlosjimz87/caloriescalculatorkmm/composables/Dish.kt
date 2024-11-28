package org.carlosjimz87.caloriescalculatorkmm.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Dish(
    modifier: Modifier = Modifier,
    image : ImageBitmap,
    contentDescription : String = "",
    size: Dp = 150.dp,
    zRotation : Float = 0f,
    x : Dp = 0.dp,
    y : Dp = 0.dp,
    ) {
    Box(
        modifier = modifier
            .offset(x, y)
            .size(size)
            .graphicsLayer(rotationZ = zRotation),
        contentAlignment = Alignment.Center
    ) {
        Image(
            bitmap = image,
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize()
        )
    }
}