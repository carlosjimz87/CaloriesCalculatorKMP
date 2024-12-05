package org.carlosjimz87.caloriescalculatorkmm.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.carlosjimz87.caloriescalculatorkmm.models.BottomTab

@Composable
fun MainScaffold(
    selectedTabIndex: Int,
    onTabSelectedIndex: (Int) -> Unit,
    content: @Composable () -> Unit
) {
    Scaffold(
        bottomBar = {
            CustomBottomNavigationBar(
                selectedIndex = selectedTabIndex,
                onItemSelected = onTabSelectedIndex
            )
        },
        content = { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                content()
            }
        }
    )
}