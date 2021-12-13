package com.akyasstore.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.akyasstore.databinding.ListSayurBinding
import com.akyasstore.model.DataBarangModel



class SayurAdapter(private  val showDetail :(String) -> Unit) :
    ListAdapter<DataBarangModel,SayurAdapter.ViewHolder>(DiffCallback()) {



   inner class ViewHolder(private val binding:ListSayurBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(DataSayur: DataBarangModel){
            binding.itemSayur = DataSayur
            binding.imgGambar.setOnClickListener {
                showDetail(DataSayur.id_brg.toString())
            }

            binding.executePendingBindings()
        }
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListSayurBinding.inflate(LayoutInflater.from(viewGroup.context)))

    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val itemSayur = getItem(position)
        viewHolder.bind(itemSayur)
    }



    class DiffCallback: DiffUtil.ItemCallback<DataBarangModel>(){
        override fun areItemsTheSame(oldItem: DataBarangModel, newItem: DataBarangModel): Boolean {
            return  oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: DataBarangModel, newItem: DataBarangModel): Boolean {
            return oldItem.id_brg == newItem.id_brg
        }

    }

}
