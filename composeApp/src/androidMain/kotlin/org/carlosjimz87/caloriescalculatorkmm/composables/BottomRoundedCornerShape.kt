package org.carlosjimz87.caloriescalculatorkmm.composables

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun BottomRoundedCornerShape(radio : Dp = 50.dp) : RoundedCornerShape {
    return RoundedCornerShape(
        topStart = 0.dp,
        topEnd = 0.dp,
        bottomStart = radio,
        bottomEnd = radio
    )
}