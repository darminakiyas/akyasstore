package com.akyasstore.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.akyasstore.databinding.ListMenuBinding
import com.akyasstore.model.MenuModel


class MenuAdapter(private  val showMenu :(String) -> Unit) :
    ListAdapter<MenuModel, MenuAdapter.ViewHolder>(DiffCallback()) {


    inner  class ViewHolder(private val binding: ListMenuBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(DataMenu: MenuModel){
            binding.itemMenu = DataMenu
            binding.imgMenu.setOnClickListener {
                showMenu(DataMenu.id_kategori.toString())
            }
            binding.executePendingBindings()
        }
    }



    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListMenuBinding.inflate(LayoutInflater.from(viewGroup.context)))
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
       val item = getItem(position)
        viewHolder.bind(item)
    }




    class DiffCallback: DiffUtil.ItemCallback<MenuModel>(){
        override fun areItemsTheSame(oldItem: MenuModel, newItem: MenuModel): Boolean {
            return  oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: MenuModel, newItem: MenuModel): Boolean {
            return oldItem.id_kategori == newItem.id_kategori
        }

    }

}
