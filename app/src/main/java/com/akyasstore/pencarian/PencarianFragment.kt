package com.akyasstore.pencarian

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.akyasstore.R
import com.akyasstore.adapter.PencarianAdapter
import com.akyasstore.adapter.RekomendasiAdapter
import com.akyasstore.databinding.FragmentPencarianBinding
import com.akyasstore.detail.DetailFragmentArgs
import com.akyasstore.detail.DetailViewModel
import com.akyasstore.detail.DetailViewModelFactory
import com.akyasstore.repository.Repository


class PencarianFragment : Fragment() {

    private lateinit var binding:FragmentPencarianBinding
    private lateinit var viewModel: PencarianViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_pencarian, container, false)
        binding = FragmentPencarianBinding.inflate(inflater)

        val repository = Repository()
        val ViewModelFactory = PencarianViewModelFactory(repository)
        viewModel = ViewModelProvider(this, ViewModelFactory).get(PencarianViewModel::class.java)

        //fungsinya untuk menghubungkan pesan response loading... viewmodel ke xml
        binding.setLifecycleOwner (this)
        binding.viewModelPencarian = viewModel

        //menerima id barang
        val argIdBrg = PencarianFragmentArgs.fromBundle(requireArguments()).pencarian
        viewModel.pencarian(argIdBrg)

        //menampilkan pencarian
        val viewAdapterPencarian = PencarianAdapter({item->showDetail(item)})
        binding.recyclerViewPencarian.adapter = viewAdapterPencarian
        viewModel.itemsPencarian.observe(viewLifecycleOwner, { list->
            viewAdapterPencarian.submitList(list)
        })

        val editPencarian = binding.editPencarian
        editPencarian.setOnEditorActionListener{v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEND -> {
                    val pencarian = editPencarian.text.toString()
                    viewModel.pencarian(pencarian)
                    true
                }
                else -> false
            }
        }
        binding.imgback.setOnClickListener {
            getActivity()?.onBackPressed();
           // this.findNavController().navigate(PencarianFragmentDirections.actionPencarianFragmentToHomeFragment())
        }

        return binding.root
    }

    fun showDetail(idBrg:String){
        this.findNavController().navigate(PencarianFragmentDirections.actionPencarianFragmentToDetailFragment(idBrg))
    }



}