import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import org.carlosjimz87.caloriescalculatorkmm.R
import org.carlosjimz87.caloriescalculatorkmm.composables.CustomOutlinedTextField
import org.carlosjimz87.caloriescalculatorkmm.theme.Green

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "Password",
    primaryColor: Color = Green
) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    CustomOutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        label = label,
        primaryColor = primaryColor,
        trailingIcon = {
            if(value.isNotBlank()){
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(
                        painter = if (isPasswordVisible) painterResource(id = R.drawable.ic_eye_off) else painterResource(id = R.drawable.ic_eye),
                        contentDescription = null,
                        tint = primaryColor
                    )
                }
            }
        },
        readOnly = false,
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
    )
}