package com.example.room.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.room.R
import com.example.room.databinding.FragmentListBinding
import com.example.room.databinding.FragmentUpdateBinding
import com.example.room.fragments.list.ListAdapter
import com.example.room.model.UserEntity
import com.example.room.viewmodel.UserViewModel

class UpdateFragment : Fragment() {
    private val args by navArgs<UpdateFragmentArgs>()
    lateinit var mViewModel:UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= FragmentUpdateBinding.inflate(layoutInflater, container, false)
        mViewModel=ViewModelProvider(this).get(UserViewModel::class.java)
        view.firstName.setText(args.currentUser.firstName)
        view.lastName.setText(args.currentUser.lastName)
        view.age.setText(args.currentUser.age.toString())
        view.updateBt.setOnClickListener {
            val fName=view.firstName.text.toString()
            val lName=view.lastName.text.toString()
            val age=Integer.parseInt(view.age.text.toString())
            if(inputCheck(fName,lName,view.age.text)){
                val updatedUser=UserEntity(args.currentUser.id,fName ,lName,age)
                mViewModel.updateUser(updatedUser)
                Toast.makeText(requireContext(), "updated successfully!!", Toast.LENGTH_LONG).show()

                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            }else
                Toast.makeText(requireContext(),"fill out all fields",Toast.LENGTH_LONG).show()
        }
        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.delete_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                return when (menuItem.itemId) {
                    R.id.delete_menu -> {
                        deleteUser()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return view.root
    }
    private fun inputCheck(fName:String,lName: String,age: Editable): Boolean {
        return !(TextUtils.isEmpty(fName)&& TextUtils.isEmpty(lName)&&age.isEmpty())
    }
    fun deleteUser(){
        val builder= AlertDialog.Builder(requireContext())
        builder.setPositiveButton("YES"){_,_->
            mViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(), "${args.currentUser.firstName} deleted successfully!!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("NO"){_,_->}
        builder.setTitle("Delete ${args.currentUser.firstName}")
        builder.setMessage("Are You Sure You Want To Delete ${args.currentUser.firstName}")
        builder.create().show()
    }
}