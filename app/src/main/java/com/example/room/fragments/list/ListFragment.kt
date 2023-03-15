package com.example.room.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room.R
import com.example.room.viewmodel.UserViewModel
import com.example.room.databinding.FragmentListBinding

class ListFragment : Fragment() {
    private lateinit var mViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view= FragmentListBinding.inflate(layoutInflater, container, false)
        val adapter=ListAdapter()
        val recyclerView=view.RV
        recyclerView.adapter=adapter
        recyclerView.layoutManager=LinearLayoutManager(requireContext())
        mViewModel=ViewModelProvider(this).get(UserViewModel::class.java)
        mViewModel.readAllData.observe(viewLifecycleOwner, Observer {user->
            adapter.setData(user)
        })
        // Inflate the layout for this fragment
        view.floatingActionButton3.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
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
                        deleteAllUsers()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return view.root
    }
    fun deleteAllUsers(){
        val builder= AlertDialog.Builder(requireContext())
        builder.setPositiveButton("YES"){_,_->
            mViewModel.deleteAllUsers()
            Toast.makeText(requireContext(), "all users deleted successfully!!", Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("NO"){_,_->}
        builder.setTitle("Delete All Users?")
        builder.setMessage("Are You Sure You Want To Delete All Users?")
        builder.create().show()
    }
}