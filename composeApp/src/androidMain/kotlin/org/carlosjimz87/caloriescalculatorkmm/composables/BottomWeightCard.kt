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
import org.carlosjimz87.caloriescalculatorkmm.theme.Black
import org.carlosjimz87.caloriescalculatorkmm.theme.Gray
import org.carlosjimz87.caloriescalculatorkmm.theme.White
import org.carlosjimz87.caloriescalculatorkmm.theme.Yellow

@Composable
fun BottomWeightCard(
    modifier: Modifier = Modifier,
    desiredWeight: Int,
    background: Color = White
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .background(background),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = "DESIRED",
            style = MaterialTheme.typography.labelSmall.copy(color = Gray),
            fontWeight = FontWeight.Bold
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawCircle(color = Yellow)
                }
            }
            Text(
                text = "$desiredWeight kg",
                style = MaterialTheme.typography.bodyLarge.copy(color = Black),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomWeightCardPreview() {
    BottomWeightCard(desiredWeight = 80)
}