package org.carlosjimz87.caloriescalculatorkmm.composables

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.carlosjimz87.caloriescalculatorkmm.theme.Green


@Composable
fun CustomOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    height : Dp = 56.dp,
    readOnly: Boolean = false,
    primaryColor: Color = Green,
    leadingIcon: @Composable (() -> Unit)? = null, // Optional leading icon
    trailingIcon: @Composable (() -> Unit)? = null, // Optional leading icon
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = value,
        readOnly = readOnly,
        onValueChange = onValueChange,
        label = {
            Text(
                modifier = Modifier.focusable(false),
                text = label,
                color = primaryColor,
            )
        },
        trailingIcon = trailingIcon,
        leadingIcon = leadingIcon,
        colors =  OutlinedTextFieldDefaults.colors(
            focusedBorderColor = primaryColor,
            unfocusedBorderColor = primaryColor.copy(alpha = 0.7f),
            cursorColor = primaryColor,
            focusedTextColor = primaryColor,
            unfocusedTextColor = primaryColor.copy(alpha = 0.7f)
        ),
        modifier = modifier
            .height(height)
            .fillMaxWidth()
    )
}