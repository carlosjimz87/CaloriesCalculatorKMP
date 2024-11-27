package org.carlosjimz87.caloriescalculatorkmm.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.carlosjimz87.caloriescalculatorkmm.Constants
import org.carlosjimz87.caloriescalculatorkmm.theme.Green


@Composable
fun PhoneEditor(
    modifier: Modifier = Modifier,
    flagResource: Int,
    countryCode: String,
    onCountryCodeChange: (String) -> Unit,
    phoneNumber: String,
    onPhoneNumberChange: (String) -> Unit,
    primaryColor: Color = Green
) {
    var isDropdownExpanded by remember { mutableStateOf(false) }

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .height(56.dp)
                .wrapContentSize()
        ) {
            OutlinedTextField(
                value = countryCode,
                onValueChange = { }, // Read-only, changes handled via dropdown
                readOnly = true,
                label = null, // No label for compact design
                leadingIcon = {
                    CountryFlagIcon(resource = flagResource, desc = countryCode)
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown, // Dropdown icon
                        contentDescription = "Country Code Dropdown",
                        modifier = Modifier.clickable { isDropdownExpanded = !isDropdownExpanded }
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryColor,
                    unfocusedBorderColor = primaryColor.copy(alpha = 0.7f),
                    cursorColor = primaryColor,
                    focusedTextColor = primaryColor,
                    unfocusedTextColor = primaryColor.copy(alpha = 0.7f)
                ),
                modifier = Modifier
                    .height(56.dp) // Match height with the phone number field
                    .width(100.dp) // Adjust width for country code and flag
            )

            CustomDropdownMenu(
                countryCodes = Constants.countryList,
                expanded = isDropdownExpanded,
                onCountryCodeChange = {
                    onCountryCodeChange(it)
                    isDropdownExpanded = false
                },
                onDismissed = {
                    isDropdownExpanded = false
                })
        }

        Spacer(modifier = Modifier.width(8.dp)) // Space between country code and phone number

        // Phone Number Input
        CustomOutlinedTextField(
            value = phoneNumber,
            onValueChange = onPhoneNumberChange,
            label = "Phone"
        )
    }
}