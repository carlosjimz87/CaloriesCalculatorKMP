package org.carlosjimz87.caloriescalculatorkmm.composables

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.carlosjimz87.caloriescalculatorkmm.Constants
import org.carlosjimz87.caloriescalculatorkmm.theme.Green
import org.carlosjimz87.caloriescalculatorkmm.utils.flagFromCountryCode

@Composable
fun PhoneEditor(
    modifier: Modifier = Modifier,
    flagResource: Int,
    countryCode: String,
    phoneNumber: String,
    height: Dp = 56.dp,
    primaryColor: Color = Green,
    onPhoneNumberChange: (String) -> Unit,
    onCountryCodeChange: (String) -> Unit,
) {
    var isDropdownExpanded by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(height), // Enforce same height for the Row
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.3f)
        ) {
            // Country Code Input
            CustomOutlinedTextField(
                height = height,
                value = countryCode,
                readOnly = true,
                onValueChange = {},
                label = "",
                leadingIcon = {
                    CountryFlagIcon(resource = flagResource, desc = countryCode) {
                        isDropdownExpanded = !isDropdownExpanded
                    }
                },
                primaryColor = primaryColor
            )

            // Dropdown Menu for Country Codes
            CustomDropdownMenu(
                countryCodes = Constants.countryList,
                expanded = isDropdownExpanded,
                onCountryCodeChange = {
                    onCountryCodeChange(it)
                    isDropdownExpanded = false
                },
                onDismissed = {
                    isDropdownExpanded = false
                }
            )
        }

        // Phone Number Input
        CustomOutlinedTextField(
            height = height,
            value = phoneNumber,
            onValueChange = onPhoneNumberChange,
            label = "Phone",
        )
    }
}

@Preview
@Composable
private fun PhoneEditorPreview() {
    val countryCode = "+91"
    PhoneEditor(
        flagResource = flagFromCountryCode(countryCode),
        countryCode = countryCode,
        phoneNumber = "",
        onCountryCodeChange = {},
        onPhoneNumberChange = {}
    )
}