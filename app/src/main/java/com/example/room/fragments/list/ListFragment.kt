package com.example.room.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room.R
import com.example.room.databinding.FragmentListBinding

class ListFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view= FragmentListBinding.inflate(layoutInflater, container, false)
        val adapter=ListAdapter()
        val recyclerView=view.RV
        recyclerView.adapter=adapter
        recyclerView.layoutManager=LinearLayoutManager(requireContext())
        viewModel=ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.readAllData.observe(viewLifecycleOwner, Observer {user->
            adapter.setData(user)
        })
        // Inflate the layout for this fragment
        view.floatingActionButton3.setOnClickListener{
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
                        viewModel.deleteAllUsersView(requireContext())
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return view.root
    }
}