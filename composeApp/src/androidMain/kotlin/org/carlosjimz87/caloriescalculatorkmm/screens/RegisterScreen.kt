package org.carlosjimz87.caloriescalculatorkmm.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.carlosjimz87.caloriescalculatorkmm.animations.RotatingTableclothAnimation
import org.carlosjimz87.caloriescalculatorkmm.composables.CustomOutlinedTextField
import org.carlosjimz87.caloriescalculatorkmm.composables.DotsIndicator
import org.carlosjimz87.caloriescalculatorkmm.composables.OutlinedCustomButton
import org.carlosjimz87.caloriescalculatorkmm.composables.PhoneEditor
import org.carlosjimz87.caloriescalculatorkmm.theme.Green
import org.carlosjimz87.caloriescalculatorkmm.utils.flagFromCountryCode

@Composable
fun RegisterScreen(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    var countryCode by remember { mutableStateOf("+1") }
    var phoneNumber by remember { mutableStateOf("") }
    val reverse by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White),
        shape = RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 0.dp
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Green Container at the Top with Animation
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f) // this makes the container take 50% of the height
                    .clip(
                        RoundedCornerShape(
                            bottomEnd = 48.dp,
                            bottomStart = 48.dp
                        )
                    ) // Clip to the rounded corners
            ) {
                RotatingTableclothAnimation(
                    modifier = Modifier.fillMaxSize(),
                    reverse = reverse
                )
            }

            Spacer(modifier = Modifier.height(16.dp)) // Spacing between the container and content

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
                        style = MaterialTheme.typography.headlineMedium,
                        color = Green // Darker green
                    )
                }

            }

            Spacer(modifier = Modifier.height(24.dp)) // Spacing before form

            // Form Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                CustomOutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = "Email"
                )

                PhoneEditor(
                    flagResource = flagFromCountryCode(countryCode), // Replace with your flag resource
                    countryCode = countryCode,
                    onCountryCodeChange = { countryCode = it },
                    phoneNumber = phoneNumber,
                    onPhoneNumberChange = { phoneNumber = it }
                )
            }

            Spacer(modifier = Modifier.weight(1f)) // Push button to the bottom

            // Action Button Section
            OutlinedCustomButton(bottomMargin = 32.dp) {
                // toast
                Toast.makeText(context, "TBD", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Preview(showBackground = true, apiLevel = 34)
@Composable
private fun RegisterScreenPreview() {
    MaterialTheme {
        RegisterScreen()
    }
}