package org.carlosjimz87.caloriescalculatorkmm.screens.auth.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.carlosjimz87.caloriescalculatorkmm.composables.DotsIndicator
import org.carlosjimz87.caloriescalculatorkmm.composables.EmailTextField
import org.carlosjimz87.caloriescalculatorkmm.composables.OutlinedCustomButton
import org.carlosjimz87.caloriescalculatorkmm.composables.PhoneEditor
import org.carlosjimz87.caloriescalculatorkmm.theme.Green
import org.carlosjimz87.caloriescalculatorkmm.utils.flagFromCountryCode
import org.carlosjimz87.caloriescalculatorkmm.validators.validateEmail
import org.carlosjimz87.caloriescalculatorkmm.validators.validatePhoneNumber


@Composable
fun RegisterView(
    onNextStep: () -> Unit,
) {
    var countryCode by remember { mutableStateOf("+1") }
    var phoneNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") } // State to manage the text

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                DotsIndicator(totalDots = 2, selectedIndex = 1)

                Text(
                    text = "Create an account",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = Green // Darker green
                )
            }

        }

        // Form Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            EmailTextField(email = email, onEmailChange = {email = it}, emailError = validateEmail(email))

            PhoneEditor(
                flagResource = flagFromCountryCode(countryCode), // Replace with your flag resource
                countryCode = countryCode,
                phoneNumber = phoneNumber,
                onCountryCodeChange =  { countryCode = it },
                onPhoneNumberChange = { phoneNumber = it },
                phoneError = validatePhoneNumber(countryCode, phoneNumber)
            )
        }

        Spacer(modifier = Modifier.weight(1f)) // Push button to the bottom

        // Action Button Section
        OutlinedCustomButton {
            onNextStep()
        }
    }
}

@Preview
@Composable
private fun RegisterViewPreview() {
    RegisterView(
        onNextStep = {}
    )
}