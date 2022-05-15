package com.aspk.aspk.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.aspk.aspk.databinding.FragmentAuthBinding
import com.google.android.material.tabs.TabLayoutMediator


class AuthFragment: Fragment() {

    lateinit var binding: FragmentAuthBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle)
        with(binding){
            pager.adapter = adapter
            val tabArray = arrayOf(
                "Login",
                "Sign Up"

            )
            TabLayoutMediator(tabLayout, pager) { tab, position ->
                tab.text = tabArray[position]
            }.attach()
        }

    }
}

