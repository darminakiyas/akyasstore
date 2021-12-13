package com.akyasstore.login

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.akyasstore.databinding.FragmentLoginBinding
import com.akyasstore.home.HomeViewModel
import com.akyasstore.home.HomeViewModelFactory
import com.akyasstore.registrasi.RegistrasiFragmentDirections
import com.akyasstore.repository.Repository


class LoginFragment : Fragment() {
    private lateinit var binding:FragmentLoginBinding
    private lateinit var viewModel:LoginViewModel
    private val email_User = "emailUser"
    private val statusLogin = "statusLogin"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_login, container, false)
        binding = FragmentLoginBinding.inflate(inflater)

        val repository = Repository()
        val viewModelFactory = LoginViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(LoginViewModel::class.java)
        //fungsinya untuk menghubungkan pesan response loading... viewmodel ke xml
        binding.setLifecycleOwner (this)
        binding.viewModelLogin = viewModel



        viewModel.success.observe(viewLifecycleOwner,{list->
            if (list == 0){
                Toast.makeText(context,"Email Anda Belum Terdaftar ", Toast.LENGTH_SHORT).show()
                binding.txtPesan.text = "Email Anda Belum Terdaftar"
            }else if(list == 1){
                binding.txtPesan.text = "Password Anda Salah"
                Toast.makeText(context,"Password Anda Salah ", Toast.LENGTH_SHORT).show()
            }else {
                binding.txtPesan.text = "Selamat Anda Berhasil Login"
                Toast.makeText(context,"Selamat Anda Berhasil Login ", Toast.LENGTH_SHORT).show()

                saveData()
                this.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToAkunFragment2())
            }
        })

        binding.btnLogin.setOnClickListener{
            login()
        }

        binding.txtDaftar.setOnClickListener{
            this.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegestrasiFragment())
        }

        return binding.root
    }

    fun login() {
         if (binding.edtEmail.text.isEmpty()) {
            binding.edtEmail.error = "Kolom Email Tidak Boleh Kosong"
            binding.edtEmail.requestFocus()
            return
        } else if (binding.edtPassword.text.isEmpty()) {
            binding.edtPassword.error = "Kolom Password Tidak Boleh Kosong"
            binding.edtPassword.requestFocus()
            return
        }

        viewModel.login(
            binding.edtEmail.text.toString(),
            binding.edtPassword.text.toString()
        )
    }

    private fun saveData(){
        val emailUser = binding.edtEmail.text.toString()

        val sharedPrederences = context?.getSharedPreferences("AkyasStore",Context.MODE_PRIVATE)
        val editor = sharedPrederences?.edit()
        editor!!.apply(){
            putString(email_User, emailUser)
            putString(statusLogin, true.toString())
        }.apply()

    }




}