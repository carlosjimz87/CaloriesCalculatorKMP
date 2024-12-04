package org.carlosjimz87.caloriescalculatorkmm.composables

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import org.carlosjimz87.caloriescalculatorkmm.theme.Green

@Composable
fun CustomOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    readOnly: Boolean = false,
    primaryColor: Color = Green,
    leadingIcon: @Composable (() -> Unit)? = null, // Optional leading icon
    trailingIcon: @Composable (() -> Unit)? = null, // Optional trailing icon
    visualTransformation: VisualTransformation = VisualTransformation.None, // Add visual transformation support
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        readOnly = readOnly,
        visualTransformation = visualTransformation, // Apply the transformation here
        label = {
            Text(
                modifier = Modifier.focusable(false),
                text = label,
                color = primaryColor,
            )
        },
        trailingIcon = trailingIcon,
        leadingIcon = leadingIcon,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = primaryColor,
            unfocusedBorderColor = primaryColor.copy(alpha = 0.7f),
            cursorColor = primaryColor,
            focusedTextColor = primaryColor,
            unfocusedTextColor = primaryColor.copy(alpha = 0.7f)
        ),
        modifier = modifier
            .height(IntrinsicSize.Min)
            .fillMaxWidth()
    )
}