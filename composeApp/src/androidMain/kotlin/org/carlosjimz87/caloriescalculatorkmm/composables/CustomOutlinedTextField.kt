package org.carlosjimz87.caloriescalculatorkmm.composables

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
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
    keyboardType: KeyboardType? = null,
    primaryColor: Color = Green,
    errorColor: Color = MaterialTheme.colorScheme.error,
    leadingIcon: @Composable (() -> Unit)? = null, // Optional leading icon
    trailingIcon: @Composable (() -> Unit)? = null, // Optional trailing icon
    visualTransformation: VisualTransformation = VisualTransformation.None, // Add visual transformation support
) {
    OutlinedTextField(
        value = value,
        onValueChange = { newValue ->
            if (keyboardType == KeyboardType.Number && newValue.all { it.isDigit() }) {
                onValueChange(newValue)
            }
        },
        readOnly = readOnly,
        visualTransformation = visualTransformation, // Apply the transformation here
        label = {
            Text(
                text = label,
                color = if(error!=null) errorColor else primaryColor,
            )
        },
        trailingIcon = trailingIcon,
        leadingIcon = leadingIcon,
        isError = error != null,
        colors = getColors(error, primaryColor, errorColor),
        modifier = modifier
            .height(IntrinsicSize.Min)
            .fillMaxWidth(),
        keyboardOptions =  KeyboardOptions.Default.copy(keyboardType = keyboardType ?: KeyboardType.Text),
        )
    // Display the error message if any
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
            errorTextColor = errorColor,
            focusedBorderColor = errorColor,
            unfocusedBorderColor = errorColor.copy(alpha = 0.7f),
            cursorColor = errorColor,
            focusedTextColor = errorColor,
            unfocusedTextColor = errorColor.copy(alpha = 0.7f)
        )
    } else {
        OutlinedTextFieldDefaults.colors(
            errorTextColor = Color.Unspecified,
            focusedBorderColor = primaryColor,
            unfocusedBorderColor = primaryColor.copy(alpha = 0.7f),
            cursorColor = primaryColor,
            focusedTextColor = primaryColor,
            unfocusedTextColor = primaryColor.copy(alpha = 0.7f)
        )
    }
}
