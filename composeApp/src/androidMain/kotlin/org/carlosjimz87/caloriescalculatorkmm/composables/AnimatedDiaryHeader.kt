package org.carlosjimz87.caloriescalculatorkmm.composables


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.carlosjimz87.caloriescalculatorkmm.Constants.caloriesList
import org.carlosjimz87.caloriescalculatorkmm.theme.Gray

@Composable
fun AnimatedDiaryHeader(modifier: Modifier = Modifier, animated : Boolean = true) {

    // Animation states
    val initialOffsetHeader = -100f
    val headerDuration = 500
    val slideOffset = remember { Animatable(initialOffsetHeader) }

    // Trigger animations for the header
    LaunchedEffect(Unit) {
        launch {
            slideOffset.animateTo(
                targetValue = 0f,
                animationSpec = tween(durationMillis = headerDuration, easing = FastOutSlowInEasing)
            )
        }
    }
    Column(modifier = modifier
        .offset(y = if(animated) slideOffset.value.dp else 0.dp)) {

        Text(
            text = "Daily calories",
            style = MaterialTheme.typography.displayLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 16.dp, bottom = 8.dp)
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(horizontal = 4.dp)
                .fillMaxWidth()
        ) {
            caloriesList.forEach { (label, value) ->
                Column(horizontalAlignment = Alignment.Start) {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        color = Gray
                    )
                    Text(
                        text = value,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AnimatedDiaryHeaderPreview() {
    AnimatedDiaryHeader(animated = false)
}