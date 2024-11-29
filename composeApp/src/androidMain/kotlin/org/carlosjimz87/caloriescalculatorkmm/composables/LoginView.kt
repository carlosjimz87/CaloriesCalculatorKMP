package org.carlosjimz87.caloriescalculatorkmm.composables
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.carlosjimz87.caloriescalculatorkmm.theme.Green
import org.carlosjimz87.caloriescalculatorkmm.utils.flagFromCountryCode

@Composable
fun LoginView(onBack: () -> Unit, forgotPassword: () -> Unit) {
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
                DotsIndicator(totalDots = 2, selectedIndex = 0)

                Text(
                    text = "Welcome back!",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Green
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

        }
        // Form Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            CustomOutlinedTextField(value = "", onValueChange = {}, label = "Email")
            CustomOutlinedTextField(value = "", onValueChange = {}, label = "Password")
            Text(
                text = "Forgot your password?",
                style = MaterialTheme.typography.bodySmall,
                color = Green,
                modifier = Modifier.align(Alignment.End)
                    .clickable {
                        forgotPassword()
                    }
            )
        }

        Spacer(modifier = Modifier.weight(1f)) // Push button to the bottom

        // Action Button Section
        OutlinedCustomButton(bottomMargin = 32.dp) {
            onBack()
        }

    }
}

@Preview
@Composable
private fun LoginViewPreview() {
    LoginView(onBack = {}, forgotPassword = {})
}