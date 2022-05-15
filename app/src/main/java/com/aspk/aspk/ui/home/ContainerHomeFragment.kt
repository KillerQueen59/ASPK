package com.aspk.aspk.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aspk.aspk.databinding.FragmentContainerHomeBinding

class ContainerHomeFragment: Fragment() {

    lateinit var binding: FragmentContainerHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContainerHomeBinding.inflate(inflater,container,false)

        return binding.root
    }
}