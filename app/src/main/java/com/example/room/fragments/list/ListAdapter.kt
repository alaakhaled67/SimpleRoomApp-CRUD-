package com.example.room.fragments.list

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.room.R
import com.example.room.databinding.ListItemBinding
import com.example.room.model.UserEntity

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var userList=emptyList<UserEntity>()
    private lateinit var binding: ListItemBinding
    class MyViewHolder(
        private val binding: ListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserEntity) {
            binding.user = user
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        binding= ListItemBinding.inflate(LayoutInflater.from(p0.context),p0,false)
        return MyViewHolder(binding) }
    override fun getItemCount(): Int {
        return userList.size }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        val current_user=userList[p1]
        p0.bind(current_user)
        p0.itemView.findViewById<ConstraintLayout>(R.id.list_item).setOnClickListener {
            val action=ListFragmentDirections.actionListFragmentToUpdateFragment(current_user)
            p0.itemView.findNavController().navigate(action)
        }
    }
    fun setData(user:List<UserEntity>){
        this.userList=user
        notifyDataSetChanged()
    }
}