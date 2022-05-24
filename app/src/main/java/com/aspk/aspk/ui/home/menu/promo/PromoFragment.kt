package com.aspk.aspk.ui.home.menu.promo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aspk.aspk.databinding.FragmentPromoBinding


class PromoFragment: Fragment() {

    lateinit var binding: FragmentPromoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPromoBinding.inflate(inflater,container,false)
        return binding.root
    }
}