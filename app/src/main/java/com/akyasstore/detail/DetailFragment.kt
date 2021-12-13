package com.akyasstore.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.akyasstore.adapter.RekomendasiAdapter
import com.akyasstore.databinding.FragmentDetailBinding
import com.akyasstore.repository.Repository


class DetailFragment : Fragment() {
    private lateinit var binding:FragmentDetailBinding
    private lateinit var viewModel:DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_detail, container, false)
        binding = FragmentDetailBinding.inflate(inflater)


        val repository = Repository()
        val ViewModelFactory = DetailViewModelFactory(repository)
        viewModel = ViewModelProvider(this, ViewModelFactory).get(DetailViewModel::class.java)

        //fungsinya untuk menghubungkan pesan response loading... viewmodel ke xml
        binding.setLifecycleOwner (this)
        binding.viewModelDetail = viewModel

        //menerima id barang
        val argIdBrg = DetailFragmentArgs.fromBundle(requireArguments()).idBrg
        viewModel.detailBarang(argIdBrg)

        //menampilkan rekomendasi
        val viewAdapterRekomendasi = RekomendasiAdapter({item->showDetail(item)})
        binding.recyclerViewRekomendasi.adapter = viewAdapterRekomendasi
        viewModel.itemsRekomendasi.observe(viewLifecycleOwner, { list->
            viewAdapterRekomendasi.submitList(list)
        })

        binding.imgFavorit.setOnClickListener {
            viewModel.likeFavorit("2",argIdBrg)
            Toast.makeText(context,"Berhasil Tambah Favorit",Toast.LENGTH_SHORT).show()
            viewModel.statusFavorit.observe(viewLifecycleOwner, { list->
                binding.textView6.text = list.toString()
            })
        }


        binding.imgback.setOnClickListener {
           // this.findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToHomeFragment())
            getActivity()?.onBackPressed();
        }


        return binding.root
    }

    fun showDetail(idBrg:String){
        viewModel.detailBarang(idBrg)
    }



}