package org.carlosjimz87.caloriescalculatorkmm.screens.auth.views
import PasswordTextField
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
import org.carlosjimz87.caloriescalculatorkmm.composables.FilledCustomButton
import org.carlosjimz87.caloriescalculatorkmm.theme.Green
import org.carlosjimz87.caloriescalculatorkmm.validators.validateEmail
import org.carlosjimz87.caloriescalculatorkmm.validators.validatePassword

@Composable
fun LoginView(onBack: () -> Unit, forgotPassword: () -> Unit) {

    var email by remember { mutableStateOf("") } // State to manage the text
    var pass by remember { mutableStateOf("") } // State to manage the text


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
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.padding(bottom = 8.dp).fillMaxWidth()
            ) {
                Text(
                    text = "Welcome back!",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = Green // Darker green
                )

                DotsIndicator(totalDots = 2, selectedIndex = 0)

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
            EmailTextField(email = email, onEmailChange = {email = it}, emailError = validateEmail(email))
            PasswordTextField(value = pass, onPasswordChange = {pass = it}, passError = validatePassword(pass))

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

        FilledCustomButton {
            onBack()
        }
    }
}

@Preview
@Composable
private fun LoginViewPreview() {
    LoginView(onBack = {}, forgotPassword = {})
}