package org.carlosjimz87.caloriescalculatorkmm.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.carlosjimz87.caloriescalculatorkmm.models.BottomTab
import org.carlosjimz87.caloriescalculatorkmm.theme.Green
import org.carlosjimz87.caloriescalculatorkmm.theme.White

@Composable
fun CustomBottomNavigationBar(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {

    BottomAppBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = Green,
        contentColor = White,
        tonalElevation = 4.dp
    ) {
        BottomTab.entries.forEachIndexed { index, item ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onItemSelected(index) },
                contentAlignment = Alignment.Center
            ) {
                if (index == selectedIndex) {
                    // Selected Item
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .background(
                                color = White,
                                shape = RoundedCornerShape(50)
                            )
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
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
                    }
                } else {
                    // Unselected Item
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

@Preview(showBackground = true)
@Composable
fun CustomBottomNavigationBarPreview() {
    var selectedIndex by remember { mutableIntStateOf(0) }
    CustomBottomNavigationBar(selectedIndex = selectedIndex) {
        selectedIndex = it
    }
}