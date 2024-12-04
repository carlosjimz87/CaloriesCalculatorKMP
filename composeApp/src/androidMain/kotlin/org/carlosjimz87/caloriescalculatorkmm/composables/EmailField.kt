package org.carlosjimz87.caloriescalculatorkmm.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable

@Composable
fun EmailField(
    email: String,
    onEmailChange: (String) -> Unit,
    emailError: String?,
) {
    Column {

        CustomOutlinedTextField(value = email, onValueChange = {
            onEmailChange(it)}, label = "Email", error = emailError )
    }
}
