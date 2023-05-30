import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

@Composable
fun DateTextField(
    label: String,
    date: Date?,
    onDateChanged: (Date?) -> Unit,
    modifier: Modifier = Modifier
) {
    var textFieldValue by remember { mutableStateOf(TextFieldValue(date?.toString() ?: "")) }

    OutlinedTextField(
        value = textFieldValue,
        onValueChange = { value ->
            textFieldValue = value

            val parsedDate = try {
                SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(value.text)
            } catch (e: Exception) {
                null
            }

            onDateChanged(parsedDate)
        },
        label = { Text(text = label) },
        modifier = modifier,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        textStyle = TextStyle.Default,
        singleLine = true
    )
}
