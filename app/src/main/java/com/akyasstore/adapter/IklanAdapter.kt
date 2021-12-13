package com.akyasstore.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.akyasstore.databinding.ListIklanBinding
import com.akyasstore.model.IklanModel


class IklanAdapter():
    ListAdapter<IklanModel,IklanAdapter.ViewHolder>(DiffCallback()) {



    class ViewHolder(private val binding:ListIklanBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(DataIklan: IklanModel) {
            binding.itemIklan = DataIklan
            binding.executePendingBindings()
        }


    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListIklanBinding.inflate(LayoutInflater.from(viewGroup.context)))
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = getItem(position)
        viewHolder.bind(item)
    }


    class DiffCallback: DiffUtil.ItemCallback<IklanModel>(){
        override fun areItemsTheSame(oldItem: IklanModel, newItem: IklanModel): Boolean {
            return  oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: IklanModel, newItem: IklanModel): Boolean {
            return oldItem.id == newItem.id
        }

    }
}
