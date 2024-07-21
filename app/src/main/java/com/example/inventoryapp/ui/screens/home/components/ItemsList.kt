package com.example.inventoryapp.ui.screens.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.inventoryapp.R
import com.example.inventoryapp.data.room.entity.Item

@Composable
fun ItemsList(modifier: Modifier = Modifier, items: List<Item>, onClick: (item: Item) -> Unit) {
    LazyColumn(modifier = modifier) {
        items(items) {
            ItemDetails(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp), item = it, onClick = onClick)
        }
    }
}

@Composable
fun ItemDetails(modifier: Modifier = Modifier, item: Item, onClick: (item: Item) -> Unit = {}) {
    Card(modifier = modifier.fillMaxWidth(), onClick = { onClick(item) }) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                Text(text = stringResource(R.string.typeS, item.type.name))
                Text(text = stringResource(R.string.nameS, item.name))
                Text(text = stringResource(R.string.descriptionS, item.description))
            }
        }
    }
}