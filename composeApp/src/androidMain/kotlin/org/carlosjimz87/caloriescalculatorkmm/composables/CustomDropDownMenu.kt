package org.carlosjimz87.caloriescalculatorkmm.composables

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun CustomDropdownMenu(
    countryCodes : Map<String, String>,
    expanded: Boolean = false,
    onCountryCodeChange: (String) -> Unit,
    onDismissed: () -> Unit,
) {
    DropdownMenu(
    expanded = expanded,
    onDismissRequest = onDismissed
    ) {

        countryCodes.forEach { (code, country) ->
            DropdownMenuItem(
                text = { Text("$code ($country)") },
                onClick = {
                    onCountryCodeChange(code)
                }
            )
        }
    }
}