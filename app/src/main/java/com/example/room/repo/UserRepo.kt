package com.example.room.repo

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Query
import com.example.room.data.userDAO
import com.example.room.model.UserEntity

class UserRepo(private val userDAO: userDAO) {
    val readAllData:LiveData<List<UserEntity>> =userDAO.readAllUsers()
 suspend fun addUser(userEntity: UserEntity){
     userDAO.addUser(userEntity)
 }
    suspend fun updateUser(userEntity: UserEntity){
        userDAO.updateUser(userEntity)
    }

    suspend fun deleteUser(userEntity: UserEntity){
        userDAO.deleteUser(userEntity)
    }

    suspend fun deleteAllUsers(){
        userDAO.deleteAllUsers()
    }
}