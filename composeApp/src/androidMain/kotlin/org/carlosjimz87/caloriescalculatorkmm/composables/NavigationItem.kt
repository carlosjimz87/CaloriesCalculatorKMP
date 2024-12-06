package org.carlosjimz87.caloriescalculatorkmm.composables


import CaloriesCalculatorTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.carlosjimz87.caloriescalculatorkmm.models.BottomTab
import org.carlosjimz87.caloriescalculatorkmm.theme.Green
import org.carlosjimz87.caloriescalculatorkmm.theme.White


@Composable
fun RowScope.NavigationItem(
    item: BottomTab,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .weight(1f)
            .clickable(
                onClick = onClick,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ),
        contentAlignment = Alignment.Center
    ) {
        if (isSelected) {
            // Selected Tab
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(imageVector = item.icon, contentDescription = item.title, tint = Green)
                Text(text = item.title, style = MaterialTheme.typography.bodyMedium, color = Green)
            }
        } else {
            // Unselected Tab
            Icon(imageVector = item.icon, contentDescription = item.title, tint = White)
        }
    }
}

@Composable
fun TabItemRoundShape(
    color : Color = White,
    navBarItemOffset: Dp,
    itemWidth: Dp,
    itemHeight: Dp,
    roundedCorner: Dp = 50.dp
    ) {
    Box(
        modifier = Modifier
            .offset(x = navBarItemOffset)
            .size(itemWidth, itemHeight)
            .background(color, RoundedCornerShape(roundedCorner))
    )
}

@Preview
@Composable
private fun NavigationItemPreview() {

    CaloriesCalculatorTheme {
        Row {
            NavigationItem(item = BottomTab.Diary, isSelected = true, onClick = {})
            NavigationItem(item = BottomTab.Goals, isSelected = false, onClick = {})
            NavigationItem(item = BottomTab.Stats, isSelected = false, onClick = {})
            NavigationItem(item = BottomTab.Preferences, isSelected = false, onClick = {})
        }
    }
}