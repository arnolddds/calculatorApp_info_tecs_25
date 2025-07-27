import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sobolev.calculatorapp_info_tecs_25.presentation.ui.screens.calculator.CalculatorState
import com.sobolev.calculatorapp_info_tecs_25.presentation.ui.screens.calculator.CalculatorViewModel


@Composable
fun CalculatorScreen(
    viewModel: CalculatorViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    var input1 by remember { mutableStateOf("") }
    var input2 by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 128.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = input1,
            onValueChange = { input1 = it },
            label = { Text("Число 1") },
            singleLine = true
        )

        OutlinedTextField(
            value = input2,
            onValueChange = { input2 = it },
            label = { Text("Число 2") },
            singleLine = true
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = { viewModel.calculate(input1, input2, "+") }) {
                Text("+")
            }
            Button(onClick = { viewModel.calculate(input1, input2, "-") }) {
                Text("-")
            }
            Button(onClick = { viewModel.calculate(input1, input2, "*") }) {
                Text("×")
            }
            Button(onClick = { viewModel.calculate(input1, input2, "/") }) {
                Text("÷")
            }
        }

        Text(
            text = when (state) {
                is CalculatorState.Success -> "Результат: ${(state as CalculatorState.Success).result}"
                is CalculatorState.Error -> "Ошибка: ${(state as CalculatorState.Error).expression}"
                else -> ""
            },
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

