package com.example.room.fragments.update

import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.text.Editable
import android.text.TextUtils
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.BaseObservable
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import androidx.lifecycle.*
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.room.R
import com.example.room.data.UserDatabase
import com.example.room.model.UserEntity
import com.example.room.repo.UserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UpdateViewModel(application:Application):AndroidViewModel(application){
    private val repo: UserRepo
    init {
        val userDAO= UserDatabase.gatDatabase(application).userDao()
        repo= UserRepo(userDAO)
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
    fun inputCheck(User:UserEntity): Boolean {
        return !(TextUtils.isEmpty(User.firstName)&& TextUtils.isEmpty(User.lastName))
    }
    fun OnInputTrue(User:UserEntity){
        updateUser(User)
    }
}