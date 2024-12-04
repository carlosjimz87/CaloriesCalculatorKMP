package org.carlosjimz87.caloriescalculatorkmm.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun CountryFlagIcon(
    resource: Int,
    desc: String,
    onClick: (() -> Unit)? = null
) {
    Icon(
        painter = painterResource(id = resource),
        contentDescription = desc,
        tint = Color.Unspecified,
        modifier = Modifier
            .size(24.dp)
            .clickable(enabled = onClick != null) { onClick?.invoke() }
    )
}