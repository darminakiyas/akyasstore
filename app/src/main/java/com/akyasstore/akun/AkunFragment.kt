package com.akyasstore.akun

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.akyasstore.R
import com.akyasstore.databinding.FragmentAkunBinding
import com.akyasstore.home.HomeFragmentDirections
import kotlinx.android.synthetic.main.fragment_akun.*


class AkunFragment : Fragment() {
    private lateinit var binding:FragmentAkunBinding
    private val email_User = "emailUser"
    private val statusLogin = "statusLogin"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_akun, container, false)
        binding = FragmentAkunBinding.inflate(inflater)
        loadData()

        binding.bottomNavigationView.setOnItemSelectedListener{item->
            when(item.itemId) {
                R.id.homeFragment -> {
                    Toast.makeText(context,"Programnya jalan", Toast.LENGTH_SHORT).show()
                    this.findNavController().navigate(AkunFragmentDirections.actionAkunFragmentToHomeFragment())
                    true
                }
                R.id.favoritFragment -> {
                    Toast.makeText(context,"Programnya jalan", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.loginFragment -> {
                    val status = "TRUE"
                    if(status == "TRUE"){
                        Toast.makeText(context,"Status Nya TRUE", Toast.LENGTH_SHORT).show()
                        this.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLoginFragment())
                    }else{
                        Toast.makeText(context,"Status Nya Salah", Toast.LENGTH_SHORT).show()
                        this.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAkunFragment())
                    }


                    true
                }
                else -> false
            }
        }

        binding.btnLogin.setOnClickListener {
            val sharedPrederences = context?.getSharedPreferences("AkyasStore",Context.MODE_PRIVATE)
            val editor = sharedPrederences?.edit()
            editor!!.apply(){
                putString(statusLogin, true.toString())
            }.apply()
            Log.d("coba","email: "+statusLogin)
        }
        binding.btnLogout.setOnClickListener {
            val sharedPrederences = context?.getSharedPreferences("AkyasStore",Context.MODE_PRIVATE)
            val editor = sharedPrederences?.edit()
            editor!!.apply(){
                putString(statusLogin, false.toString())
            }.apply()
            Log.d("coba","email: "+statusLogin)
        }



        return binding.root
    }

    private fun loadData(){
       val sharedPrederences = context?.getSharedPreferences("AkyasStore", Context.MODE_PRIVATE)
        val email_User = sharedPrederences?.getString("emailUser",null)
        val status_Login = sharedPrederences?.getString("statusLogin",null)
        Log.d("coba","email: "+email_User)
        Log.d("coba","Status :"+status_Login)
    }




}