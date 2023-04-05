package com.example.room.fragments.add

import android.app.Application
import android.text.Editable
import android.text.TextUtils
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.room.data.UserDatabase
import com.example.room.model.UserEntity
import com.example.room.repo.UserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddViewModel(application: Application):AndroidViewModel(application){
    private val repo: UserRepo
    init {
        val userDAO= UserDatabase.gatDatabase(application).userDao()
        repo= UserRepo(userDAO)
    }
    fun addUser(user: UserEntity){
        viewModelScope.launch(Dispatchers.IO){
            repo.addUser(user)
        }
    }
    fun inputCheck(fName:String,lName: String,age: Editable): Boolean {
        return !(TextUtils.isEmpty(fName)&& TextUtils.isEmpty(lName)&&age.isEmpty())
    }
    fun insertDataToDataBase(firstName:String,lastName:String,age:Editable) {
            val user = UserEntity(0, firstName, lastName, age.toString().toInt())
            addUser(user)
    }
}