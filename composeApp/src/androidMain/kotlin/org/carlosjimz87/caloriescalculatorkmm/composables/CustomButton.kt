package org.carlosjimz87.caloriescalculatorkmm.composables
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.carlosjimz87.caloriescalculatorkmm.theme.Green


@Composable
fun FilledCustomButton(
    modifier: Modifier = Modifier,
    contentColor: Color = Color.White, // Default content color
    backgroundColor: Color = Green,   // Default background color
    text: String = "Login",
    textSize: TextUnit = 22.sp,
    height: Dp = 64.dp,
    topMargin: Dp = 0.dp,
    bottomMargin: Dp = 32.dp,
    horizontalPadding: Dp = 48.dp,
    onClick: () -> Unit
) {
    Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Button(
            onClick = onClick,
            modifier = modifier
                .padding(
                    start = 0.dp,
                    end = 0.dp,
                    top = topMargin,
                    bottom = bottomMargin
                )
                .height(height),
            shape = RoundedCornerShape(50), // Rounded button
            colors = ButtonDefaults.buttonColors(
                containerColor = backgroundColor,
                contentColor = contentColor
            )
        ) {
            Text(
                modifier = Modifier.padding(horizontal = horizontalPadding),
                fontSize = textSize,
                text = text
            )
        }
    }
}

@Preview(showBackground = true, apiLevel = 34)
@Composable
private fun FilledCustomButtonPreview() {
    FilledCustomButton {}
}