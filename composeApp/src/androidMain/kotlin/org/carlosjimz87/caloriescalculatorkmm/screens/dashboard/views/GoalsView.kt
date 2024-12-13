package org.carlosjimz87.caloriescalculatorkmm.screens.dashboard.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.carlosjimz87.caloriescalculatorkmm.composables.BottomWeightCard
import org.carlosjimz87.caloriescalculatorkmm.composables.HumanFormWithThreshold
import org.carlosjimz87.caloriescalculatorkmm.composables.TopWeightCard
import org.carlosjimz87.caloriescalculatorkmm.theme.White
import org.carlosjimz87.caloriescalculatorkmm.utils.percent

@Composable
fun GoalsView(
    modifier: Modifier = Modifier,

) {
    val currentWeight: Int = 85
    val weightChange: Int = -2
    val daysAgo: Int = 5
    val startingWeight: Int = 87
    val desiredWeight: Int = 82
    val thresholdFraction: Float = 0.5f

    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = White)
            .padding(16.dp)
    ) {
        // Overlay the top card
        TopWeightCard(
            currentWeight = currentWeight,
            weightChange = weightChange,
            daysAgo = daysAgo,
            startingWeight = startingWeight,
            background = White,
        )

        // Human form as the background
        HumanFormWithThreshold(
            modifier = modifier.padding(vertical = 24.dp), // Add padding to position cards correctly
            thresholdFraction = thresholdFraction
        )

        // Overlay the bottom card
        BottomWeightCard(
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            desiredWeight = desiredWeight
        )
    }
}

@Preview
@Composable
fun PreviewWeightCardWithHumanForm() {
    GoalsView()
}