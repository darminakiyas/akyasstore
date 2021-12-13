package com.akyasstore.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.akyasstore.databinding.ListSemuaBinding
import com.akyasstore.model.DataBarangModel



class SemuaAdapter(private  val showDetail :(String) -> Unit) :
    ListAdapter<DataBarangModel,SemuaAdapter.ViewHolder>(DiffCallback()) {



   inner class ViewHolder(private val binding:ListSemuaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(DataSayur: DataBarangModel){
            binding.itemSemua = DataSayur
            binding.imgGambar.setOnClickListener {
                showDetail(DataSayur.id_brg.toString())
            }

            binding.executePendingBindings()
        }
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListSemuaBinding.inflate(LayoutInflater.from(viewGroup.context)))

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
