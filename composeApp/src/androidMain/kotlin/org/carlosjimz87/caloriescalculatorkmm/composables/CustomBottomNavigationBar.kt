package org.carlosjimz87.caloriescalculatorkmm.composables

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import org.carlosjimz87.caloriescalculatorkmm.models.BottomTab
import org.carlosjimz87.caloriescalculatorkmm.theme.Green
import org.carlosjimz87.caloriescalculatorkmm.theme.White

@Composable
fun CustomBottomNavigationBar(
    selectedIndex: Int,
    initAlpha : Float = 0f,
    animated : Boolean = true,
    onItemSelected: (Int) -> Unit,
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val withBar = LocalConfiguration.current.screenWidthDp.dp
    val heightBar = 100.dp
    var startOffset by remember { mutableStateOf(screenHeight + heightBar) }
    var startAlpha by remember { mutableFloatStateOf(initAlpha) }
    val withItem = 100.dp
    val heightItem = 40.dp
    val millis = 500

    // Slide-up animation
    val animatedOffset by animateDpAsState(
        targetValue = startOffset,
        animationSpec = tween(durationMillis = millis, easing = FastOutSlowInEasing),
        label = "BottomBarSlideUp"
    )

    // Define animation offset based on the selected index
    val animatedTabOffset by animateDpAsState(
        targetValue = selectedIndex * withBar / 4,
        label = "TabTranslationAnimation"
    )

    LaunchedEffect(Unit) {
        startOffset = 0.dp
        startAlpha = 1f
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = if(animated) animatedOffset else 0.dp) // Initial position off-screen off-screen
    ) {
        BottomAppBar(
            modifier = Modifier
                .width(withBar),
            containerColor = Green,
            contentColor = White,
            tonalElevation = 4.dp // Remove shadow elevation
        ) {
            // Add a Box to contain all items and the animated background
            Box(
                modifier = Modifier.height(heightItem) // Match height of BottomAppBar
            ) {
                // Animated White Background
                Box(
                    modifier = Modifier
                        .offset(x = if(animated) animatedTabOffset else 0.dp)
                        .size(withItem, heightItem) // Adjust size to match the visual design
                        .background(
                            color = White,
                            shape = RoundedCornerShape(50)
                        ),
                )

                // Add navigation items
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BottomTab.entries.forEachIndexed { index, item ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clickable(
                                    onClick = { onItemSelected(index) },
                                    indication = null, // Remove shadow on tap
                                    interactionSource = remember { MutableInteractionSource() }
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            if (index == selectedIndex) {
                                // Selected Tab
                                Row(
                                    modifier = Modifier.fillMaxHeight(), // Ensure full height for centering
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Icon(
                                        imageVector = item.icon,
                                        contentDescription = item.title,
                                        tint = Green
                                    )
                                    Text(
                                        text = item.title,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = Green
                                    )
                                }
                            } else {
                                // Unselected Tab
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.title,
                                    tint = White
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomBottomNavigationBarPreview() {
    var selectedIndex by remember { mutableIntStateOf(0) }
    CustomBottomNavigationBar(selectedIndex = selectedIndex, animated = false) {
        selectedIndex = it
    }
}