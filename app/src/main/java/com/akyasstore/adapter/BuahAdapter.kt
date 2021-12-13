package com.akyasstore.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.akyasstore.databinding.ListBuahBinding
import com.akyasstore.model.DataBarangModel



class BuahAdapter(private  val showDetail :(String) -> Unit) :
    ListAdapter<DataBarangModel,BuahAdapter.ViewHolder>(DiffCallback()) {


   inner class ViewHolder(private val binding:ListBuahBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(DataBuah: DataBarangModel) {
            binding.itemBuah = DataBuah
            binding.imgGambar.setOnClickListener {
                showDetail(DataBuah.id_brg.toString())
            }
            binding.executePendingBindings()
        }
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListBuahBinding.inflate(LayoutInflater.from(viewGroup.context)))
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = getItem(position)
        viewHolder.bind(item)

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
