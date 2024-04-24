package com.jossidfactory.composables.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jossidfactory.composables.data.database.dao.UserDao
import com.jossidfactory.composables.data.database.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class ComposablesDataBase: RoomDatabase() {
    abstract fun getUserDao(): UserDao
}