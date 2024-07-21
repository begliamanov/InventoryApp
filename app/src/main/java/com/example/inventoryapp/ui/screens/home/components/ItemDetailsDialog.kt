package com.example.inventoryapp.ui.screens.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.inventoryapp.R
import com.example.inventoryapp.data.room.entity.ItemType

@Composable
fun ItemDetailsDialog(
    modifier: Modifier = Modifier,
    actionContent: @Composable () -> Unit,
    onDismiss: () -> Unit,
    itemType: ItemType,
    onTypeSelect: (ItemType) -> Unit,
    name: String,
    onNameChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = { focusManager.clearFocus() }
        ) {
            Column(modifier = Modifier.padding(all = 16.dp)) {
                OutlinedTextField(value = name, onValueChange = onNameChange, label = {
                    Text(
                        text = stringResource(
                            R.string.name
                        )
                    )
                })
                OutlinedTextField(
                    value = description,
                    onValueChange = onDescriptionChange,
                    label = { Text(text = stringResource(R.string.description)) })
                Spacer(modifier = Modifier.height(8.dp))
                TypeDropdown(value = itemType, onSelect = onTypeSelect)
                Spacer(modifier = Modifier.height(16.dp))
                actionContent()
            }
        }
    }
}