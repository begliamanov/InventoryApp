package com.example.inventoryapp.data.room.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.inventoryapp.data.room.dao.ItemDao
import com.example.inventoryapp.data.room.entity.Item

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class InventoryDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile
        private var Instance: InventoryDatabase? = null
        fun getDatabase(context: Context) : InventoryDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, InventoryDatabase::class.java, "inventory_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}