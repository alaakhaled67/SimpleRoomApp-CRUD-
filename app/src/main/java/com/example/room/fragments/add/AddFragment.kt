package com.example.room.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.room.R
import com.example.room.model.UserEntity
import com.example.room.viewmodel.UserViewModel
import com.example.room.databinding.FragmentAddBinding

class AddFragment : Fragment() {
    lateinit var mViewModel: UserViewModel
lateinit var binding:FragmentAddBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        mViewModel=ViewModelProvider(this).get(UserViewModel::class.java)
        binding.addBt.setOnClickListener {
            insertDataToDataBase()
        }
        return binding.root
    }

    private fun inputCheck(fName:String,lName: String,age:Editable): Boolean {
return !(TextUtils.isEmpty(fName)&&TextUtils.isEmpty(lName)&&age.isEmpty())
    }

    private fun insertDataToDataBase() {
        val firstName=binding.firstName.text.toString()
        val lastName=binding.lastName.text.toString()
        val age=binding.age.text
        if(inputCheck(firstName,lastName,age)){
         val user= UserEntity(0,firstName,lastName,age.toString().toInt())
            mViewModel.addUser(user)
            Toast.makeText(requireContext(),"successfully added",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else
            Toast.makeText(requireContext(),"fill out all fields",Toast.LENGTH_LONG).show()

    }
}