package com.akyasstore.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.akyasstore.databinding.ListCarouselBinding
import com.akyasstore.model.CarouselModel


class CarouselAdapter() :
    ListAdapter<CarouselModel, CarouselAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(private var binding:ListCarouselBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(DataCarousel: CarouselModel) {
            binding.itemCarousel = DataCarousel
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListCarouselBinding.inflate(LayoutInflater.from(viewGroup.context)))
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = getItem(position)
        viewHolder.bind(item)
    }


    class DiffCallback: DiffUtil.ItemCallback<CarouselModel>(){
        override fun areItemsTheSame(oldItem: CarouselModel, newItem: CarouselModel): Boolean {
            return  oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: CarouselModel, newItem: CarouselModel): Boolean {
            return oldItem.id == newItem.id
        }

    }

}
