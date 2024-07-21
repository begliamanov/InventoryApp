package com.example.inventoryapp.base

import android.app.Application
import android.content.Context
import com.example.inventoryapp.data.room.dao.ItemDao
import com.example.inventoryapp.data.room.db.InventoryDatabase
import com.example.inventoryapp.data.room.repoImpl.ItemRepoImpl
import com.example.inventoryapp.domain.repo.ItemRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideItemDao(context: Context): ItemDao = InventoryDatabase.getDatabase(context).itemDao()

    @Provides
    @Singleton
    fun provideItemRepo(itemDao: ItemDao): ItemRepo = ItemRepoImpl(itemDao)
}

