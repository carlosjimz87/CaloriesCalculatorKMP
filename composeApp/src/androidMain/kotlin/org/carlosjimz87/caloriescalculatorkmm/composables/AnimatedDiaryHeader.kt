package org.carlosjimz87.caloriescalculatorkmm.composables


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.carlosjimz87.caloriescalculatorkmm.Constants.caloriesList
import org.carlosjimz87.caloriescalculatorkmm.theme.Gray

@Composable
fun AnimatedDiaryHeader(
    headerSlideOffset: Animatable<Float, AnimationVector1D>,
    headerAlpha: Animatable<Float, AnimationVector1D>
) {
    Text(
        text = "Daily calories",
        style = MaterialTheme.typography.displayLarge,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .offset(y = headerSlideOffset.value.dp)
            .alpha(headerAlpha.value)
            .padding(bottom = 8.dp)
    )

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp)
            .offset(y = headerSlideOffset.value.dp)
            .alpha(headerAlpha.value)
    ) {
        caloriesList.forEach { (label, value) ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.headlineSmall,
                    color = Gray
                )
                Text(
                    text = value,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}