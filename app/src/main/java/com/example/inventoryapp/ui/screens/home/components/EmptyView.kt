package com.example.inventoryapp.ui.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.inventoryapp.R

@Composable
fun EmptyView(modifier: Modifier = Modifier) {
        Column( modifier = modifier, verticalArrangement = Arrangement.Center) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.empty),
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.add_new_items),
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
    }
}