package com.example.room.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.room.data.UserDatabase
import com.example.room.repo.UserRepo
import com.example.room.model.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application):AndroidViewModel(application){
    val readAllData:LiveData<List<UserEntity>>
    private val repo: UserRepo
    init {
        val userDAO= UserDatabase.gatDatabase(application).userDao()
        repo= UserRepo(userDAO)
        readAllData=repo.readAllData
    }
    fun addUser(user: UserEntity){
        viewModelScope.launch(Dispatchers.IO){
            repo.addUser(user)
        }
    }
    fun updateUser(user: UserEntity){
        viewModelScope.launch(Dispatchers.IO){
            repo.updateUser(user)
        }
    }
    fun deleteUser(userEntity: UserEntity){
        viewModelScope.launch(Dispatchers.IO){
            repo.deleteUser(userEntity)
        }
    }

     fun deleteAllUsers(){
         viewModelScope.launch(Dispatchers.IO){
             repo.deleteAllUsers()
         }
    }
}