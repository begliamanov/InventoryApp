package com.example.inventoryapp.data.room.repoImpl

import com.example.inventoryapp.data.room.dao.ItemDao
import com.example.inventoryapp.data.room.entity.Item
import com.example.inventoryapp.domain.repo.ItemRepo
import kotlinx.coroutines.flow.Flow

class ItemRepoImpl(private val itemDao: ItemDao) : ItemRepo {
    override fun getAll(): Flow<List<Item>> = itemDao.getAll()

    override fun getItem(id: Int): Flow<Item> = itemDao.getItem(id)

    override suspend fun insert(item: Item) = itemDao.insert(item)

    override suspend fun update(item: Item) = itemDao.update(item)

    override suspend fun delete(item: Item) = itemDao.delete(item)
}