package com.example.pichanga.components

import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.pichanga.CalendarViewModel

@Composable
fun CreateEventDialog(
    show: Boolean,
    viewModel: CalendarViewModel,
    modifier: Modifier = Modifier,
    onDismiss: ()-> Unit,
    onButtonClick: () -> Unit,
){
    val state = viewModel.state

    if(show){
        Dialog(
            onDismissRequest = {
                onDismiss()
            },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            ),
        ) {
            Column(modifier = modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(12.dp))
                .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Crea nuevo evento", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = state.title,
                    onValueChange = { viewModel.onValueChangeTitle(it) },
                    label = { Text(text = "Titulo")}
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = state.date,
                    onValueChange = { viewModel.onValueChangeDate(it) },
                    label = { Text(text = "Fecha")}
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = state.time,
                    onValueChange = { viewModel.onValueChangeTime(it) },
                    label = { Text(text = "Hora")},
                )
                Spacer(modifier = Modifier.height(20.dp))

                DropMenu(viewModel)
                Spacer(modifier = Modifier.height(40.dp))

                Button(onClick = { onButtonClick() }) {
                    Text(text = "enviar")
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropMenu(viewModel: CalendarViewModel){
    val state = viewModel.state

    var isExpanded by remember {
        mutableStateOf(false)
    }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = {isExpanded = it}
    ) {
        TextField(
            value = state.repeat,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ){
            DropdownMenuItem(
                onClick = {
                    viewModel.onValueChangeRepeat("Siempre")
                    isExpanded = false
                }
            ){
                Text(text = "Siempre")
            }

            DropdownMenuItem(
                onClick = {
                    viewModel.onValueChangeRepeat("Solo hoy")
                    isExpanded = false
                }
            ){
                Text(text = "Solo hoy")
            }
        }
    }
}