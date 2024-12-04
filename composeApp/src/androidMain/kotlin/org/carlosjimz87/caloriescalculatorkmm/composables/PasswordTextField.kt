import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
    label: String = "Password",
    primaryColor: Color = Green,
    errorColor: Color = MaterialTheme.colorScheme.error,
    onPasswordChange: (String) -> Unit,
    passError: String?,
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    Column {

        CustomOutlinedTextField(
            modifier = modifier,
            value = value,
            onValueChange = { onPasswordChange(it) },
            error = passError,
            label = label,
            primaryColor = primaryColor,
            trailingIcon = {
                if(value.isNotBlank()){
                    IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                        Icon(
                            painter = if (isPasswordVisible) painterResource(id = R.drawable.ic_eye_off) else painterResource(id = R.drawable.ic_eye),
                            contentDescription = null,
                            tint = if(passError!=null) errorColor else primaryColor
                        )
                    }
                }
            },
            readOnly = false,
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
        )
    }

}