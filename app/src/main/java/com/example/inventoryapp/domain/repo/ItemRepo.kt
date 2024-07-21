package com.example.inventoryapp.domain.repo

import com.example.inventoryapp.data.room.entity.Item
import kotlinx.coroutines.flow.Flow

interface ItemRepo {
    fun getAll(): Flow<List<Item>>
    fun getItem(id: Int): Flow<Item>
    suspend fun insert(item: Item)
    suspend fun update(item: Item)
    suspend fun delete(item: Item)
}