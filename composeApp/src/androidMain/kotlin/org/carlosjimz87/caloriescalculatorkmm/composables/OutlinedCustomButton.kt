package org.carlosjimz87.caloriescalculatorkmm.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.carlosjimz87.caloriescalculatorkmm.theme.Green


@Composable
fun OutlinedCustomButton(
    modifier: Modifier = Modifier,
    contentColor: Color = Green,
    borderColor: Color = Green,
    text : String = "Next step",
    textSize: TextUnit = 16.sp,
    height : Dp = 50.dp,
    onClick: () -> Unit
) {

    OutlinedButton(
    onClick = onClick,
    modifier = modifier
    .fillMaxWidth()
    .padding(horizontal = 16.dp)
    .height(height),
    shape = RoundedCornerShape(50), // Rounded button
    border = BorderStroke(2.dp, borderColor), // Change border color
    colors = ButtonDefaults.outlinedButtonColors(
    contentColor = contentColor
    )
    ) {
        Text(
            fontSize = textSize,
            text = text
        )
    }
}