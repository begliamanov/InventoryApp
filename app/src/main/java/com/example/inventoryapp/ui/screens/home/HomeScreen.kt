package com.example.inventoryapp.ui.screens.home

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.inventoryapp.R
import com.example.inventoryapp.ui.screens.home.components.EmptyView
import com.example.inventoryapp.ui.screens.home.components.ItemDetailsDialog
import com.example.inventoryapp.ui.screens.home.components.ItemsList

enum class ActionType { Add, Edit }

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.getAllItems()
    }

    var showDialog by remember { mutableStateOf(false) }
    var actionType by remember { mutableStateOf(ActionType.Add) }



    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showDialog = true
                },
                modifier = Modifier.navigationBarsPadding()
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.add_item)
                )
            }
        }
    ) { innerPadding ->
        if (showDialog) {
            ItemDetailsDialog(
                actionContent = {
                    when (actionType) {
                        ActionType.Add -> {
                            Button(modifier = Modifier.fillMaxWidth(), onClick = {
                                viewModel.addItem()
                                showDialog = false
                            }) {
                                Text(text = "Add")
                            }
                        }

                        ActionType.Edit -> {
                            Column {
                                Button(modifier = Modifier.fillMaxWidth(), onClick = {
                                    viewModel.updateItem()
                                    showDialog = false
                                }) {
                                    Text(text = "Update")
                                }
                                OutlinedButton(
                                    modifier = Modifier.fillMaxWidth(),
                                    onClick = {
                                        viewModel.deleteItem()
                                        showDialog = false
                                    },
                                    border = BorderStroke(1.dp, Color.Red)
                                ) {
                                    Text(text = "Delete", color = Color.Red)
                                }
                            }
                        }
                    }
                },
                onDismiss = { showDialog = false },
                itemType = uiState.itemType,
                onTypeSelect = viewModel::updateItemType,
                name = uiState.name,
                onNameChange = viewModel::updateName,
                description = uiState.description,
                onDescriptionChange = viewModel::updateDescription
            )
        }
        Crossfade(
            modifier = Modifier.padding(innerPadding), targetState = uiState.items.isEmpty(),
            label = "Items exist"
        ) { isEmpty ->
            if (isEmpty) {
                EmptyView(modifier = Modifier.fillMaxSize())
            } else {
                ItemsList(
                    items = uiState.items,
                    onClick = {
                        viewModel.updateName(it.name)
                        viewModel.updateDescription(it.description)
                        viewModel.updateItemType(it.type)
                        viewModel.updateSelectedItemId(it.id)
                        showDialog = true
                        actionType = ActionType.Edit
                    }
                )
            }
        }
    }
}

