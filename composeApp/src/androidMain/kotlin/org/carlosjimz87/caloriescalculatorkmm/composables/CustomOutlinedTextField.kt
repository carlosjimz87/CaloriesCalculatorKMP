package org.carlosjimz87.caloriescalculatorkmm.composables

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import org.carlosjimz87.caloriescalculatorkmm.theme.Green

@Composable
fun CustomOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    readOnly: Boolean = false,
    error: String? = null,
    primaryColor: Color = Green,
    errorColor: Color = MaterialTheme.colorScheme.error,
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
        isError = error != null,
        colors = getColors(error, primaryColor, errorColor),
        modifier = modifier
            .height(IntrinsicSize.Min)
            .fillMaxWidth()
    )
    if (error != null) {
        Text(
            text = error,
            color = errorColor,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
        )
    }
}

@Composable
fun getColors(error: String?, primaryColor: Color, errorColor: Color): TextFieldColors {
    return if(error!=null){
        return OutlinedTextFieldDefaults.colors(
            focusedBorderColor = errorColor,
            unfocusedBorderColor = errorColor.copy(alpha = 0.7f),
            cursorColor = errorColor,
            focusedTextColor = errorColor,
            unfocusedTextColor = errorColor.copy(alpha = 0.7f)
        )
    } else {
        OutlinedTextFieldDefaults.colors(
            focusedBorderColor = primaryColor,
            unfocusedBorderColor = primaryColor.copy(alpha = 0.7f),
            cursorColor = primaryColor,
            focusedTextColor = primaryColor,
            unfocusedTextColor = primaryColor.copy(alpha = 0.7f)
        )
    }
}
