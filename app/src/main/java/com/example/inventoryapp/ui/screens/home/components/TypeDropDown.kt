package com.example.inventoryapp.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.inventoryapp.R
import com.example.inventoryapp.data.room.entity.ItemType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TypeDropdown(
    modifier: Modifier = Modifier,
    value: ItemType,
    onSelect: (ItemType) -> Unit,
) {
    val options = ItemType.entries.map { it.name }
    var isExpanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = isExpanded,
        onExpandedChange = { isExpanded = !isExpanded }
    ) {
        OutlinedTextField(
            modifier = Modifier.menuAnchor(),
            value = value.name,
            onValueChange = {},
            readOnly = true,
            label = { Text(text = stringResource(R.string.type)) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            }
        )
        DropdownMenu(
            modifier = Modifier
                .background(Color.White)
                .exposedDropdownSize(),
            expanded = isExpanded,
            onDismissRequest = {
                isExpanded = false
            }
        ) {
            options.forEach {
                DropdownMenuItem(
                    text = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = it,
                        )
                    },
                    onClick = {
                        val type = ItemType.valueOf(it)
                        onSelect(type)
                        isExpanded = false
                    }
                )
            }
        }
    }
}