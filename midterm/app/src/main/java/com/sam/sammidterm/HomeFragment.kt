package com.sam.sammidterm

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sam.sammidterm.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater,
            R.layout.fragment_home, container, false)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

//        viewModel.getAll()

        binding.recycleHome.adapter = HomeAdapter(viewModel)

        binding.floatingAdd.setOnClickListener {
            findNavController().navigate(R.id.insertFragment)
        }
        (activity as MainActivity).getAll()
        (activity as MainActivity).setArticles.observe(viewLifecycleOwner, Observer {
            Log.d("sam", "setArticles=${viewModel.setArticles.value}")
            (binding.recycleHome.adapter as HomeAdapter).submitList(it)
        })

        return binding.root
    }
}
