package org.carlosjimz87.caloriescalculatorkmm.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.carlosjimz87.caloriescalculatorkmm.theme.Black
import org.carlosjimz87.caloriescalculatorkmm.theme.Gray
import org.carlosjimz87.caloriescalculatorkmm.theme.LightGreen
import org.carlosjimz87.caloriescalculatorkmm.theme.White
import org.carlosjimz87.caloriescalculatorkmm.theme.Yellow

@Composable
fun TopWeightCard(
    modifier: Modifier = Modifier,
    currentWeight: Int,
    weightChange: Int,
    daysAgo: Int,
    startingWeight: Int,
    background : Color = White
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            modifier = modifier.background(background),
            text = "CURRENT WEIGHT",
            style = MaterialTheme.typography.bodySmall.copy(color = Black),
            fontWeight = FontWeight.Bold
        )
        Row(
            modifier = modifier.background(background),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                modifier = modifier.background(background),
                text = "$currentWeight kg",
                style = MaterialTheme.typography.displayLarge.copy(color = LightGreen),
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = modifier.padding(top = 4.dp).background(background),
                text = if (weightChange > 0) "+$weightChange kg" else "$weightChange kg",
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 14.sp, color = Gray)
            )
        }
        Text(
            modifier = modifier.background(background),
            text = "last weigh $daysAgo days ago",
            style = MaterialTheme.typography.bodyLarge.copy(color = Gray),
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = modifier.background(background),
            text = "STARTING",
            style = MaterialTheme.typography.labelSmall.copy(color = Gray),
            fontWeight = FontWeight.Bold
        )
        Row(
            modifier = modifier.background(background),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .align(Alignment.CenterVertically),
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawCircle(color = Yellow)
                }
            }
            Text(
                text = "$startingWeight kg",
                style = MaterialTheme.typography.bodyLarge.copy(color = Black),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun TopWeightCardPreview() {
    TopWeightCard(currentWeight = 85, weightChange = -2, daysAgo = 3, startingWeight = 82)
}