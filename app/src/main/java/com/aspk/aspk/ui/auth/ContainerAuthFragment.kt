package com.aspk.aspk.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aspk.aspk.databinding.FragmentContainerAuthBinding
import com.aspk.aspk.databinding.FragmentSignUpBinding

class ContainerAuthFragment: Fragment() {

    lateinit var binding: FragmentContainerAuthBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContainerAuthBinding.inflate(inflater,container,false)
        return binding.root
    }
}