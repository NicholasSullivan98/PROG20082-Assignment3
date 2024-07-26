package sheridan.sullnich.assignment3.ui.item.form

import android.app.TimePickerDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import sheridan.sullnich.assignment3.R
import sheridan.sullnich.assignment3.domain.Category
import sheridan.sullnich.assignment3.ui.common.formatDate
import sheridan.sullnich.assignment3.ui.common.formatTime
import sheridan.sullnich.assignment3.ui.item.model.ListItemFormModel
import sheridan.sullnich.assignment3.ui.theme.Assignment3Theme
import java.util.Calendar
import java.util.Currency
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListItemForm(
    listItemFormModel: ListItemFormModel,
    modifier: Modifier = Modifier,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onCategoryChange: (Category) -> Unit,
    onPriorityChange: (Float) -> Unit,
    onDateChange: (Date) -> Unit,
    enabled: Boolean = true
) {
    var showDatePicker by rememberSaveable { mutableStateOf(false) }
    var showTimePicker by rememberSaveable { mutableStateOf(false) }

    // date picker component
    if (showDatePicker) {
        val day: Calendar = Calendar.getInstance().apply {
            timeInMillis = listItemFormModel.date.time
        }
        val zoneOffset = day.timeZone.getOffset(day.time.time)
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = day.timeInMillis + zoneOffset,
            yearRange = 2024..2027
        )
        DatePickerDialog(
            onDismissRequest = {
                showDatePicker = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        val selected: Calendar = Calendar.getInstance().apply {
                            timeInMillis = datePickerState.selectedDateMillis !! - zoneOffset
                        }
                        val calendar: Calendar = Calendar.getInstance().apply {
                            timeInMillis = listItemFormModel.date.time
                            set(
                                selected.get(Calendar.YEAR),
                                selected.get(Calendar.MONTH),
                                selected.get(Calendar.DAY_OF_MONTH)
                            )
                        }
                        onDateChange(calendar.time)
                        showDatePicker = false
                    }
                ) {
                    Text(text = stringResource(R.string.ok))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDatePicker = false
                    }
                ) {
                    Text(text = stringResource(R.string.cancel))
                }
            }
        ) {
            DatePicker(
                state = datePickerState
            )
        }
    }

    // time picker component
    if (showTimePicker) {
        val calendar: Calendar = Calendar.getInstance().apply { time = listItemFormModel.date }
        val timePickerState = rememberTimePickerState(
            initialHour = calendar.get(Calendar.HOUR_OF_DAY),
            initialMinute = calendar.get(Calendar.MINUTE),
            is24Hour = false
        )
        TimePickerDialog(
            onDismissRequest = {
                showTimePicker = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        with(calendar){
                            set(Calendar.HOUR_OF_DAY, timePickerState.hour)
                            set(Calendar.MINUTE, timePickerState.minute)
                        }
                        onDateChange(calendar.time)
                        showTimePicker = false
                    }
                ) {
                    Text(text = stringResource(R.string.ok))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showTimePicker = false
                    }
                ) {
                    Text(text = stringResource(R.string.cancel))
                }
            }
        ) {
            TimePicker(state = timePickerState)
        }
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ) {
        OutlinedTextField(
            value = listItemFormModel.title,
            onValueChange = onTitleChange,
            label = { Text(stringResource(R.string.listitem_name_req)) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = listItemFormModel.description,
            onValueChange = onDescriptionChange,
            label = { Text(stringResource(R.string.listitem_description_req)) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = stringResource(id = R.string.priority),
                style = MaterialTheme.typography.bodyLarge
            )
            PriorityInput(
                priority = listItemFormModel.priority,
                onPriorityChange = onPriorityChange
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = stringResource(id = R.string.category),
                style = MaterialTheme.typography.bodyLarge
            )
            CategoryInput(
                category = listItemFormModel.category,
                onCategoryChange = onCategoryChange
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ){
            OutlinedButton(
                onClick = { showDatePicker = true }
            ) {
                Text(text = formatDate(listItemFormModel.date))
            }
            OutlinedButton(
                onClick = { showTimePicker = true }
            ) {
                Text(text = formatTime(listItemFormModel.date))
            }
        }
        if (enabled) {
            Text(
                text = stringResource(R.string.required_fields),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_medium))
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun ProductFormPreview() {
    Assignment3Theme {
        ListItemForm(
            listItemFormModel =
            ListItemFormModel(
                title = "Item name", description = "Item description"
            ),
            onTitleChange = {}, onDescriptionChange = {},
            onCategoryChange = {}, onPriorityChange = {}, onDateChange = {}
        )
    }
}