package com.akyasstore.home


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.akyasstore.R
import com.akyasstore.adapter.*
import com.akyasstore.databinding.FragmentHomeBinding
import com.akyasstore.repository.Repository
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel:HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home, container, false)
        binding = FragmentHomeBinding.inflate(inflater)



        val repository = Repository()
        val viewModelFactory = HomeViewModelFactory(repository)

        viewModel = ViewModelProvider(this,viewModelFactory).get(HomeViewModel::class.java)



        //fungsinya untuk menghubungkan pesan response loading... viewmodel ke xml
        binding.setLifecycleOwner (this)
        binding.viewModel = viewModel

        viewModel.jumlahFavorit.observe(viewLifecycleOwner, { list->
            binding.txtJumlahFavorit.text = list.toString()
            Log.d("data",list.toString())
        })

        //carousel
        val viewAdapterCarousel = CarouselAdapter()
        binding.recyclerViewCarousel.adapter = viewAdapterCarousel
        viewModel.carousel.observe(viewLifecycleOwner, { list->
            viewAdapterCarousel.submitList(list)
        })

        //menu
        val viewAdapterMenu = MenuAdapter({item->showMenu(item)})
        binding.recyclerViewMenu.adapter = viewAdapterMenu
        viewModel.menu.observe(viewLifecycleOwner, { list->
            viewAdapterMenu.submitList(list)
        })

        //sayur
        val viewAdapterSayur = SayurAdapter({item->showDetail(item)})
        binding.recyclerViewSayur.adapter = viewAdapterSayur
        viewModel.sayur.observe(viewLifecycleOwner, { list->
            viewAdapterSayur.submitList(list)
        })
        //buah
        val viewAdapterBuah = BuahAdapter({item->showDetail(item)})
        binding.recyclerViewBuah.adapter = viewAdapterBuah
        viewModel.buah.observe(viewLifecycleOwner, { list->
            viewAdapterBuah.submitList(list)
        })

        //iklan
        val viewAdapterIklan = IklanAdapter()
        binding.recyclerViewIklan.adapter = viewAdapterIklan
        viewModel.iklan.observe(viewLifecycleOwner, { list->
            viewAdapterIklan.submitList(list)
        })

        //daging
        val viewAdapterDaging = DagingAdapter({item->showDetail(item)})
        binding.recyclerViewDaging.adapter = viewAdapterDaging
        viewModel.daging.observe(viewLifecycleOwner, { list->
            viewAdapterDaging.submitList(list)
        })

        //sembako
        val viewAdapterSebako = SembakoAdapter({item->showDetail(item)})
        binding.recyclerViewSembako.adapter = viewAdapterSebako
        viewModel.sembako.observe(viewLifecycleOwner, { list->
            viewAdapterSebako.submitList(list)
        })

        binding.txtLihatSemua1.setOnClickListener {
            val idKategori = "1"
            this.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLihatSemuFragment(idKategori))
        }
        binding.txtLihatSemua2.setOnClickListener {
            val idKategori = "2"
            this.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLihatSemuFragment(idKategori))
        }
        binding.txtLihatSemua3.setOnClickListener {
            val idKategori = "3"
            this.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLihatSemuFragment(idKategori))
        }
        binding.txtLihatSemua4.setOnClickListener {
            val idKategori = "4"
            this.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLihatSemuFragment(idKategori))
        }

        val editPencarian = binding.editPencarian
        editPencarian.setOnEditorActionListener{v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEND -> {
                    val pencarian = editPencarian.text.toString()
                    this.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToPencarianFragment(pencarian))
                    Toast.makeText(context,"Jalan Program",Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }




        binding.bottomNavigationView.setOnItemSelectedListener{item->

            when(item.itemId) {
                R.id.favoritFragment -> {
                   Toast.makeText(context,"Programnya jalan", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.loginFragment -> {
                    loadData()
                    true
                }
                else -> false
            }
        }




        return binding.root
    }

    fun showMenu(idKategori:String){
        this.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLihatSemuFragment(idKategori))
    }

    fun showDetail(idBrg:String){
        this.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(idBrg))
    }
    //untuk pesan ketika ingin keluar dari aplikasi


    private fun loadData(){
        val sharedPrederences = context?.getSharedPreferences("AkyasStore", Context.MODE_PRIVATE)
        val status_Login = sharedPrederences?.getString("statusLogin",null)

        if(status_Login == true.toString()){
            Toast.makeText(context,"Status Nya TRUE", Toast.LENGTH_SHORT).show()
            this.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAkunFragment())
        }else{
            Toast.makeText(context,"Status Nya Salah", Toast.LENGTH_SHORT).show()
            this.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLoginFragment())
        }
    }



}