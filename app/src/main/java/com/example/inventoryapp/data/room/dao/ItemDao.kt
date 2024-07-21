package com.example.inventoryapp.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.inventoryapp.data.room.entity.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Query("SELECT * FROM items")
    fun getAll(): Flow<List<Item>>

    @Query("SELECT * FROM items WHERE id = :id")
    fun getItem(id: Int): Flow<Item>

    // When the user tries to add an existing Item into the database Room ignores the conflict.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    @Update
    suspend fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)
}