package com.example.room.fragments.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.room.R
import com.example.room.databinding.FragmentAddBinding

class AddFragment : Fragment() {
    lateinit var addViewModel:AddViewModel
    lateinit var binding:FragmentAddBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        addViewModel=ViewModelProvider(this).get(AddViewModel::class.java)
        binding.addBt.setOnClickListener {
            if (addViewModel.inputCheck(
                    binding.firstName.text.toString(),
                    binding.lastName.text.toString(),
                    binding.age.text
                )
            ) {
                addViewModel.insertDataToDataBase(
                    binding.firstName.text.toString(),
                    binding.lastName.text.toString(),
                    binding.age.text
                )
                Toast.makeText(
                    requireContext(),
                    "successfully added",
                    Toast.LENGTH_LONG
                ).show()
                findNavController().navigate(R.id.action_addFragment_to_listFragment)
            } else
                Toast.makeText(
                    requireContext(),
                    "fill out all fields",
                    Toast.LENGTH_LONG
                ).show()
        }
        return binding.root
    }
}