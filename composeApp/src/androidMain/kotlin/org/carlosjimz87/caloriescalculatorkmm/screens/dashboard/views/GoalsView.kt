package org.carlosjimz87.caloriescalculatorkmm.screens.dashboard.views

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import org.carlosjimz87.caloriescalculatorkmm.composables.BottomWeightCard
import org.carlosjimz87.caloriescalculatorkmm.composables.HumanFormWithThreshold
import org.carlosjimz87.caloriescalculatorkmm.composables.TopWeightCard
import org.carlosjimz87.caloriescalculatorkmm.theme.LightGreen
import org.carlosjimz87.caloriescalculatorkmm.theme.White
import org.carlosjimz87.caloriescalculatorkmm.utils.percent

@Composable
fun GoalsView(
    modifier: Modifier = Modifier,
    animated : Boolean = true
) {
    // Animation state for sliding in
    val animatedOffset = remember { Animatable(initialValue = -2500f) } // Start off-screen

    LaunchedEffect(Unit) {
        animatedOffset.animateTo(
            targetValue = 0f,
            animationSpec = tween(
                durationMillis = 600,
                easing = FastOutSlowInEasing
            )
        )
    }
    Box(modifier = modifier.fillMaxSize().background(
        color = LightGreen,
    ).padding(bottom = 32.dp)
    ){
        Box(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .offset { IntOffset(x = 0, y = if(animated) animatedOffset.value.toInt() else 0) } // Slide in animation
                .background(
                    color = White,
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = 50.dp, // Rounded corners for the bottom
                        bottomEnd = 50.dp
                    )
                )
                .padding(16.dp)
        ) {
            // Human form as the background
            HumanFormWithThreshold(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp), // Add padding to position cards correctly
                thresholdFraction = 0.5f
            )

            // Overlay the top card
            TopWeightCard(
                modifier = Modifier.fillMaxWidth(),
                currentWeight = 85,
                weightChange = -2,
                daysAgo = 5,
                startingWeight = 87
            )

            // Overlay the bottom card
            BottomWeightCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                desiredWeight = 82
            )
        }
    }

}

@Preview
@Composable
fun PreviewWeightCardWithHumanForm() {
    GoalsView(animated = false)
}