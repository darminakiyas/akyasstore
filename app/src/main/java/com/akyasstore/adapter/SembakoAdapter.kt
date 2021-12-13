package com.akyasstore.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.akyasstore.databinding.ListSembakoBinding
import com.akyasstore.model.DataBarangModel



class SembakoAdapter(private  val showDetail :(String) -> Unit) :
    ListAdapter<DataBarangModel,SembakoAdapter.ViewHolder>(DiffCallback()) {



   inner class ViewHolder(private val binding:ListSembakoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(DataSembako: DataBarangModel){
            binding.itemSembako = DataSembako
            binding.imgGambar.setOnClickListener {
                showDetail(DataSembako.id_brg.toString())
            }
            binding.executePendingBindings()
        }
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListSembakoBinding.inflate(LayoutInflater.from(viewGroup.context)))
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val itemSembako = getItem(position)
        viewHolder.bind(itemSembako)


    }


   // override fun getItemCount() = dataSet.itemsSembako.value!!.size
    class DiffCallback: DiffUtil.ItemCallback<DataBarangModel>(){
        override fun areItemsTheSame(oldItem: DataBarangModel, newItem: DataBarangModel): Boolean {
            return  oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: DataBarangModel, newItem: DataBarangModel): Boolean {
            return oldItem.id_brg == newItem.id_brg
        }

    }
}
