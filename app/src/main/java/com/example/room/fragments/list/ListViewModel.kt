package com.example.room.fragments.list

import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.room.MainActivity
import com.example.room.data.UserDatabase
import com.example.room.model.UserEntity
import com.example.room.repo.UserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel(application: Application):AndroidViewModel(application) {
    val readAllData: LiveData<List<UserEntity>>
    private val repo: UserRepo
    init {
        val userDAO= UserDatabase.gatDatabase(application).userDao()
        repo= UserRepo(userDAO)
        readAllData=repo.readAllData
    }
    fun deleteAllUsers(){
        viewModelScope.launch(Dispatchers.IO){
            repo.deleteAllUsers()
        }
    }
    fun deleteAllUsersView(context: Context){
        val builder= AlertDialog.Builder(context)
        builder.setPositiveButton("YES"){_,_->
            deleteAllUsers()
            Toast.makeText(context, "all users deleted successfully!!", Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("NO"){_,_->}
        builder.setTitle("Delete All Users?")
        builder.setMessage("Are You Sure You Want To Delete All Users?")
        builder.create().show()
    }
}