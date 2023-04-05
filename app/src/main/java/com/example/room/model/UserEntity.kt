package com.example.room.model

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.ParcelField
import androidx.versionedparcelable.VersionedParcelize
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val firstName:String,
    val lastName:String,
    val age :Int):Parcelable
