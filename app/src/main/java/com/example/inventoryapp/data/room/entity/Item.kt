package com.example.inventoryapp.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity(tableName = "items")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String,
    val type: ItemType,
)

enum class ItemType {
    Desk,
    Computer,
    Keyboard,
    Server,
    Human
}