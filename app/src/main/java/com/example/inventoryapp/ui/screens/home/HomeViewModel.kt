package com.example.inventoryapp.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventoryapp.data.room.entity.Item
import com.example.inventoryapp.data.room.entity.ItemType
import com.example.inventoryapp.domain.repo.ItemRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val itemRepo: ItemRepo,
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    fun getAllItems() = viewModelScope.launch {
        itemRepo.getAll().collectLatest { items ->
            _uiState.update { it.copy(items = items) }
        }
    }

    fun updateItem() = viewModelScope.launch {
        // TODO: Add validation
        val item = Item(
            id = uiState.value.selectedItemId ?: throw IllegalStateException("Item id is null"),
            name = uiState.value.name,
            description = uiState.value.description,
            type = uiState.value.itemType
        )
        itemRepo.update(item)
        getAllItems()
    }

    fun deleteItem() = viewModelScope.launch {
        // TODO: Add validation
        val item = Item(
            id = uiState.value.selectedItemId ?: throw IllegalStateException("Item id is null"),
            name = uiState.value.name,
            description = uiState.value.description,
            type = uiState.value.itemType
        )
        itemRepo.delete(item)
    }

    fun addItem() = viewModelScope.launch {
        // TODO: Add validation
        val item = Item(
            name = uiState.value.name,
            description = uiState.value.description,
            type = uiState.value.itemType
        )
        itemRepo.insert(item)
        getAllItems()
    }

    fun updateItemType(itemType: ItemType) = _uiState.update { it.copy(itemType = itemType) }
    fun updateName(name: String) = _uiState.update { it.copy(name = name) }
    fun updateDescription(description: String) = _uiState.update { it.copy(description = description) }
    fun updateSelectedItemId(id: Int) = _uiState.update { it.copy(selectedItemId = id) }
}

data class HomeUiState(
    val items: List<Item> = emptyList(),
    val itemType: ItemType = ItemType.Desk,
    val name: String = "",
    val description: String = "",
    val selectedItemId: Int? = null,
)

