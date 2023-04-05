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
import com.example.room.model.UserEntity

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var userList=emptyList<UserEntity>()

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        return MyViewHolder( LayoutInflater.from(p0.context).inflate(R.layout.list_item, p0, false)) }
    override fun getItemCount(): Int {
        return userList.size }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        val current_user=userList[p1]
        p0.itemView.findViewById<TextView>(R.id.id_show).text=current_user.id.toString()
        p0.itemView.findViewById<TextView>(R.id.fName_show).text=current_user.firstName
        p0.itemView.findViewById<TextView>(R.id.lName_show).text=current_user.lastName
        p0.itemView.findViewById<TextView>(R.id.age_show).text=current_user.age.toString()
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