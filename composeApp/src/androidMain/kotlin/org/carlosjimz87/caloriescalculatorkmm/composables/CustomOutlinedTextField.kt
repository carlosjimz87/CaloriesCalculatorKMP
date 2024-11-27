package org.carlosjimz87.caloriescalculatorkmm.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.carlosjimz87.caloriescalculatorkmm.theme.Green


@Composable
fun CustomOutlinedTextField(
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null, // Optional leading icon
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    primaryColor: Color = Green, // Default to DarkGreen
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = label,
                color = primaryColor,
            )
        },
        leadingIcon = leadingIcon,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = primaryColor, // Border color when focused
            unfocusedBorderColor = primaryColor.copy(alpha = 0.7f), // Border color when not focused
            cursorColor = primaryColor, // Cursor color
            focusedLabelColor = primaryColor, // Label color when focused
            unfocusedLabelColor = primaryColor.copy(alpha = 0.7f) // Label color when not focused
        ),
        modifier = modifier
            .fillMaxWidth()
    )
}