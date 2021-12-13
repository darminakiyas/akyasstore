package com.akyasstore.registrasi

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.akyasstore.databinding.FragmentRegestrasiBinding
import com.akyasstore.repository.Repository


class RegistrasiFragment : Fragment() {
    private lateinit var binding:FragmentRegestrasiBinding
    private lateinit var viewModel: RegistrasiViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return inflater.inflate(R.layout.fragment_regestrasi, container, false)
        binding = FragmentRegestrasiBinding.inflate(inflater)
        val repository = Repository()
        val ViewModelFactory = RegistrasiViewModelFactory(repository)
        viewModel = ViewModelProvider(this,ViewModelFactory).get(RegistrasiViewModel::class.java)
        //fungsinya untuk menghubungkan pesan response loading... viewmodel ke xml
        binding.setLifecycleOwner (this)
        binding.viewModelRegister = viewModel

        viewModel.success.observe(viewLifecycleOwner,{list->
            if (list == 0){
                Toast.makeText(context,"Email Sudah digunakan ",Toast.LENGTH_SHORT).show()
                binding.txtpesan.text = "Email Sudah Digunakan"
            }else{
                Toast.makeText(context,"Registrasi Berhasil Di Inputkan ",Toast.LENGTH_SHORT).show()
                this.findNavController().navigate(RegistrasiFragmentDirections.actionRegestrasiFragmentToLoginFragment())
            }
        })
        binding.txtsilahkanlogin.setOnClickListener {
            this.findNavController().navigate(RegistrasiFragmentDirections.actionRegestrasiFragmentToLoginFragment())
        }


        binding.btnDaftar.setOnClickListener{
            registrasi()
        }

        return binding.root
    }
    fun registrasi() {
        if (binding.edtNamaLengkap.text.isEmpty()) {
            binding.edtNamaLengkap.error = "Kolom Nama Tidak Boleh Kosong"
            binding.edtNamaLengkap.requestFocus()
            return
        } else if (binding.edtEmail.text.isEmpty()) {
            binding.edtEmail.error = "Kolom Email Tidak Boleh Kosong"
            binding.edtEmail.requestFocus()
            return
        } else if (binding.edtAlamat.text.isEmpty()) {
            binding.edtAlamat.error = "Kolom Alamat Tidak Boleh Kosong"
            binding.edtAlamat.requestFocus()
            return
        } else if (binding.edtHP.text.isEmpty()) {
            binding.edtHP.error = "Kolom HP Tidak Boleh Kosong"
            binding.edtHP.requestFocus()
            return
        } else if (binding.edtPassword.text.isEmpty()) {
            binding.edtPassword.error = "Kolom Password Tidak Boleh Kosong"
            binding.edtPassword.requestFocus()
            return
        }

        viewModel.registrasi(
            binding.edtNamaLengkap.text.toString(),
            binding.edtEmail.text.toString(),
            binding.edtAlamat.text.toString(),
            binding.edtHP.text.toString(),
            binding.edtPassword.text.toString()
        )
    }
}