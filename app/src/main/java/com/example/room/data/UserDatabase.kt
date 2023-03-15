package com.example.room.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.room.model.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase:RoomDatabase() {
    abstract fun userDao():userDAO
    companion object{
        @Volatile
        private var INSTANCE:UserDatabase?=null
        fun gatDatabase(context: Context):UserDatabase{
            val tempInstance= INSTANCE
            if(tempInstance!=null)
                return tempInstance
            synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database").build()
                INSTANCE=instance
                return instance
            }
        }
    }
}