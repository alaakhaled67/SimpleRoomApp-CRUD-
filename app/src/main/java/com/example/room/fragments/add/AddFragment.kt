package com.example.room.fragments.add

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
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
        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.delete_menu, menu)
                val item = menu.findItem(R.id.delete_menu)
                if (item != null) item.isVisible = false
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                return when (menuItem.itemId) {
                    android.R.id.home->{
                        findNavController().popBackStack()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return binding.root
    }
}