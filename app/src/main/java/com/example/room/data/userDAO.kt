package com.example.room.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.room.model.UserEntity

@Dao
interface userDAO{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: UserEntity)

    @Update
    suspend fun updateUser(user: UserEntity)

    @Delete
    suspend fun deleteUser(user: UserEntity)

    @Query("Delete FROM user_table")
    suspend fun deleteAllUsers()

    @Query("SELECT * From user_table ORDER BY id ASC")
    fun readAllUsers():LiveData<List<UserEntity>>
}