package org.carlosjimz87.caloriescalculatorkmm.composables

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
    animated: Boolean = true,
    onItemSelected: (Int) -> Unit,
) {

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val barHeight = 100.dp
    val itemWidth = 90.dp
    val itemHeight = 40.dp
    val horizPadding = 16.dp

    // Animation Stats
    var startOffset by remember { mutableStateOf(screenHeight + barHeight) }
    val animationDuration = 500


    // Animations
    val animatedOffset by animateDpAsState(
        targetValue = startOffset,
        animationSpec = tween(durationMillis = animationDuration, easing = FastOutSlowInEasing),
        label = "BottomBarSlideUp"
    )

    val navBarItemOffset by animateDpAsState(
        targetValue = selectedIndex * (screenWidth - horizPadding*2) / BottomTab.entries.size,
        label = "NavBarItemOffset"
    )

    LaunchedEffect(Unit) {
        startOffset = 0.dp
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = if(animated) animatedOffset else 0.dp)
    ) {
        BottomAppBar(
            containerColor = Green,
            contentColor = White,
            tonalElevation = 0.dp, // Remove shadow
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(barHeight)
                    .padding(top = 4.dp)
                    .padding(horizontal = horizPadding),
            ) {
                // Animated Background for Selected Tab
                TabItemRoundShape(
                    navBarItemOffset = navBarItemOffset,
                    itemWidth = itemWidth,
                    itemHeight = itemHeight
                )

                // Navigation Items
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 6.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BottomTab.entries.forEachIndexed { index, item ->
                        NavigationItem(
                            item = item,
                            isSelected = index == selectedIndex,
                            onClick = { onItemSelected(index) }
                        )
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