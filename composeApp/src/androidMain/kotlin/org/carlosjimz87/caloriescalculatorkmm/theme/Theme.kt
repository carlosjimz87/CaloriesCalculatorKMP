import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import org.carlosjimz87.caloriescalculatorkmm.theme.Black
import org.carlosjimz87.caloriescalculatorkmm.theme.Gray
import org.carlosjimz87.caloriescalculatorkmm.theme.Green
import org.carlosjimz87.caloriescalculatorkmm.theme.LightGreen
import org.carlosjimz87.caloriescalculatorkmm.theme.White

@Composable
fun CaloriesCalculatorTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Green,
            onPrimary = White,
            secondary = LightGreen,
            background = White,
            surface = White,
            onBackground = Black,
            onSurface = Gray
        ),
        typography = MaterialTheme.typography,
        content = content
    )
}