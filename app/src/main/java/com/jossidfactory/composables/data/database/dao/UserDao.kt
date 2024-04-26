package com.jossidfactory.composables.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jossidfactory.composables.data.database.entities.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM users_table")
    suspend fun getAllUsers(): List<UserEntity>

    @Query("SELECT * FROM users_table WHERE email = :email")
    suspend fun getUserByEmail(email: String): UserEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Delete()
    suspend fun deleteUser(user: UserEntity)
}