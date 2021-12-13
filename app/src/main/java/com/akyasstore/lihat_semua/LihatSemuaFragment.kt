package com.akyasstore.lihat_semua

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.akyasstore.adapter.SemuaAdapter
import com.akyasstore.databinding.FragmentLihatSemuBinding

import com.akyasstore.repository.Repository


class LihatSemuaFragment : Fragment() {
    private lateinit var binding: FragmentLihatSemuBinding
    private lateinit var viewModel: LihatSemuaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_lihat_semu, container, false)
        binding = FragmentLihatSemuBinding.inflate(inflater)

        val repository = Repository()
        val viewModelFactory = LihatSemuaViewModelFactory(repository)

        viewModel = ViewModelProvider(this,viewModelFactory).get(LihatSemuaViewModel::class.java)

        //fungsinya untuk menghubungkan pesan response loading... viewmodel ke xml
        binding.setLifecycleOwner (this)
        binding.viewModelSemua = viewModel

        val argidkategori = LihatSemuaFragmentArgs.fromBundle(requireArguments()).idKategori
        viewModel.kategori(argidkategori)


        val viewAdapterSemua = SemuaAdapter({item->showDetail(item)})
        binding.recyclerViewSemua.adapter = viewAdapterSemua
        viewModel.itemsSemuaBarang.observe(viewLifecycleOwner,{ list->
            viewAdapterSemua.submitList(list)
        })

        binding.imgback.setOnClickListener {
            getActivity()?.onBackPressed();
            //this.findNavController().navigate(LihatSemuaFragmentDirections.actionLihatSemuFragmentToHomeFragment())
        }





        return binding.root

    }
    fun showDetail(idBrg:String){
        this.findNavController().navigate(LihatSemuaFragmentDirections.actionLihatSemuFragmentToDetailFragment(idBrg))
    }
}