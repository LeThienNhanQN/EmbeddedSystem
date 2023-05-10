package com.ldnhat.embedded_server.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ldnhat.embedded_server.R
import com.ldnhat.embedded_server.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private val homeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentHomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.homeViewModel = homeViewModel

        homeViewModel.dataButtonClick.observe(viewLifecycleOwner, {
            if (it && null != it) {
                this.findNavController().navigate(HomeFragmentDirections.actionHomeToData())
                homeViewModel.onDataButtonClickCompleted()
            }
        })

        homeViewModel.chartButtonClick.observe(viewLifecycleOwner, {
            if (null != it) {
                if (it) {
                    this.findNavController().navigate(HomeFragmentDirections.actionHomeToChart())
                    homeViewModel.onChartButtonClickCompleted()
                }
            }
        })

        binding.lifecycleOwner = this
        return binding.root
    }
}