package org.carlosjimz87.caloriescalculatorkmm.screens.auth

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.carlosjimz87.caloriescalculatorkmm.animations.RotatingTableclothAnimation
import org.carlosjimz87.caloriescalculatorkmm.screens.auth.views.LoginView
import org.carlosjimz87.caloriescalculatorkmm.screens.auth.views.RegisterView
import org.carlosjimz87.caloriescalculatorkmm.utils.openInBrowser

@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
    isRegister: Boolean = true,
    onSuccessLogin: () -> Unit = {},
    onSuccessRegister: () -> Unit = {},
) {
    var isRegisterView by remember { mutableStateOf(isRegister) }
    var reverse by remember { mutableStateOf(false) }
    val context = LocalContext.current
    Card(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White),
        shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Green Container at the Top with Animation
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .clip(
                        RoundedCornerShape(
                            bottomEnd = 48.dp,
                            bottomStart = 48.dp
                        )
                    )
            ) {
                RotatingTableclothAnimation(
                    modifier = Modifier.fillMaxSize(),
                    reverse = reverse // Pass the state to trigger animation
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Content Section Below the Animation
            AnimatedContent(
                targetState = isRegisterView,
                transitionSpec = {
                    if (targetState) {
                        slideInHorizontally { fullWidth -> fullWidth } togetherWith
                                slideOutHorizontally { fullWidth -> -fullWidth }
                    } else {
                        slideInHorizontally { fullWidth -> -fullWidth } togetherWith
                                slideOutHorizontally { fullWidth -> fullWidth }
                    }
                }, label = "context"
            ) { targetState ->
                if (targetState) {
                    // Register View
                    RegisterView(
                        onNextStep = {
                            reverse = true // Trigger reverse animation
                            isRegisterView = false
                        },
                    )
                } else {
                    // Login View
                    LoginView(
                        onBack = {
                            reverse = false // Trigger forward animation
                            isRegisterView = true
                        },
                        onLogin = {
                            onSuccessLogin()
                        },
                        forgotPassword = {
                            openInBrowser(context, "http://caloriescalculatorkmm.com/forgotPassword")
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, apiLevel = 34)
@Composable
private fun RegisterScreenPreview() {
    MaterialTheme {
        AuthScreen()
    }
}