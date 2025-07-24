import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sobolev.calculatorapp_info_tecs_25.domain.model.CalculatorState
import com.sobolev.calculatorapp_info_tecs_25.presentation.ui.screens.calculator.CalculatorViewModel
import kotlin.collections.forEach
import com.sobolev.calculatorapp_info_tecs_25.R
import com.sobolev.calculatorapp_info_tecs_25.domain.CalculatorCommand
import com.sobolev.calculatorapp_info_tecs_25.domain.model.Symbol


@Composable
fun CalculatorScreen(
    navController: NavController,
    viewModel: CalculatorViewModel,
    modifier: Modifier = Modifier
) {
    val state = viewModel.state.collectAsState()

    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .clip(RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp))
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(horizontal = 40.dp, vertical = 16.dp),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.End
                ) {
                    when (val currentState = state.value) {
                        is CalculatorState.Error -> {
                            Text(
                                text = currentState.expression,
                                fontSize = 36.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.error
                            )
                            Text(
                                text = "",
                                fontSize = 17.sp,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        }
                        is CalculatorState.Input -> {
                            Text(
                                text = currentState.expression,
                                fontSize = 36.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = currentState.result,
                                fontSize = 17.sp,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        }
                        is CalculatorState.Success -> {
                            Text(
                                text = currentState.result,
                                fontSize = 36.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = "",
                                fontSize = 17.sp,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        }
                        CalculatorState.Initial -> {}
                    }
                }


                CalculatorKeyboard { command ->
                    viewModel.processCommand(command)
                }
            }

            IconButton(
                onClick = { navController.navigate("history") },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(24.dp)
                    .size(48.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = stringResource(R.string.history)
                )
            }
        }
    }
}


@Composable
fun CalculatorKeyboard(onCommand: (CalculatorCommand) -> Unit) {
    val buttonLayout = listOf(
        listOf("AC", "( )", "%", "÷"),
        listOf("7", "8", "9", "×"),
        listOf("4", "5", "6", "-"),
        listOf("1", "2", "3", "+"),
        listOf("0", ".", "=")
    )

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        buttonLayout.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                row.forEach { label ->
                    val weight = if (label == "0") 2f else 1f
                    val aspect = if (label == "0") 2f else 1f
                    val bgColor = when (label) {
                        "AC" -> MaterialTheme.colorScheme.secondary
                        "+", "-", "×", "÷", "=", "( )", "%" -> MaterialTheme.colorScheme.tertiary
                        else -> MaterialTheme.colorScheme.primary
                    }

                    CalculatorButton(
                        symbol = label,
                        backgroundColor = bgColor,
                        modifier = Modifier
                            .weight(weight)
                            .aspectRatio(aspect)
                    ) {
                        onCommand(labelToCommand(label))
                    }
                }
            }
        }
    }
}

@Composable
fun CalculatorButton(
    symbol: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(backgroundColor)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = symbol,
            fontSize = 40.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

fun labelToCommand(label: String): CalculatorCommand = when (label) {
    "0" -> CalculatorCommand.Input(Symbol.DIGIT_0)
    "1" -> CalculatorCommand.Input(Symbol.DIGIT_1)
    "2" -> CalculatorCommand.Input(Symbol.DIGIT_2)
    "3" -> CalculatorCommand.Input(Symbol.DIGIT_3)
    "4" -> CalculatorCommand.Input(Symbol.DIGIT_4)
    "5" -> CalculatorCommand.Input(Symbol.DIGIT_5)
    "6" -> CalculatorCommand.Input(Symbol.DIGIT_6)
    "7" -> CalculatorCommand.Input(Symbol.DIGIT_7)
    "8" -> CalculatorCommand.Input(Symbol.DIGIT_8)
    "9" -> CalculatorCommand.Input(Symbol.DIGIT_9)
    ".", "," -> CalculatorCommand.Input(Symbol.DOT)
    "+" -> CalculatorCommand.Input(Symbol.ADD)
    "-" -> CalculatorCommand.Input(Symbol.SUBTRACT)
    "×" -> CalculatorCommand.Input(Symbol.MULTIPLY)
    "÷" -> CalculatorCommand.Input(Symbol.DIVIDE)
    "( )" -> CalculatorCommand.Input(Symbol.PARENTHESIS)
    "AC" -> CalculatorCommand.Clear
    "=" -> CalculatorCommand.Evaluate
    else -> throw IllegalArgumentException("Unknown label: $label")
}
