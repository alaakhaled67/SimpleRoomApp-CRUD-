package com.example.room.fragments.update

import android.icu.number.IntegerWidth
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.room.R
import com.example.room.databinding.FragmentUpdateBinding
import com.example.room.model.UserEntity

class UpdateFragment : Fragment() {
    val args by navArgs<UpdateFragmentArgs>()
    lateinit var updateViewModel:UpdateViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= FragmentUpdateBinding.inflate(layoutInflater, container, false)
        updateViewModel=ViewModelProvider(this).get(UpdateViewModel::class.java)
        view.user=args.currentUser
        view.updateBt.setOnClickListener {
            val fname=view.firstName.text
            val lname=view.lastName.text
            val age=Integer.parseInt(view.age.text.toString())
            val user=UserEntity(args.currentUser.id,fname.toString(),lname.toString(),age)
            if(updateViewModel.inputCheck(user)){
                updateViewModel.OnInputTrue(user)
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
                        updateViewModel.deleteAllUsersView(requireContext(),args.currentUser)
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return view.root
    }
}