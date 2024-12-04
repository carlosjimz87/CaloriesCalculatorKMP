package org.carlosjimz87.caloriescalculatorkmm.screens.auth.views
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.carlosjimz87.caloriescalculatorkmm.composables.CustomOutlinedTextField
import org.carlosjimz87.caloriescalculatorkmm.composables.DotsIndicator
import org.carlosjimz87.caloriescalculatorkmm.composables.OutlinedCustomButton
import org.carlosjimz87.caloriescalculatorkmm.theme.Green

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
                    text = "Wellcome back!",
                    style = MaterialTheme.typography.headlineMedium,
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
            // Form Section
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

        Spacer(modifier = Modifier.weight(1f))

        OutlinedCustomButton {
            onBack()
        }
    }
}

@Preview
@Composable
private fun LoginViewPreview() {
    LoginView(onBack = {}, forgotPassword = {})
}