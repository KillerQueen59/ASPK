package com.aspk.aspk.ui.onboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aspk.aspk.databinding.FragmentContainerOnboardBinding

class ContainerOnboardFragment: Fragment() {

    lateinit var binding: FragmentContainerOnboardBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContainerOnboardBinding.inflate(inflater,container,false)
        return binding.root
    }
}