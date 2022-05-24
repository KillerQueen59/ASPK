package com.aspk.aspk.ui.home.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.aspk.aspk.R
import com.aspk.aspk.databinding.FragmentMenuBinding

class MenuFragment: Fragment() {

    lateinit var binding: FragmentMenuBinding
    private val homeAuthController: NavController? by lazy { activity?.findNavController(R.id.fcv_home) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleClick()
    }

    private fun handleClick(){
        with(binding){
            btnPromo.setOnClickListener {
                goToPromo()
            }
        }
    }

    private fun goToPromo(){
        val direction = MenuFragmentDirections.actionMenuFragmentToPromoFragment()
        homeAuthController?.navigate(direction)
    }

    private fun goToFood(){
        val direction = MenuFragmentDirections.actionMenuFragmentToPromoFragment()
        homeAuthController?.navigate(direction)
    }

    private fun goToDrink(){
        val direction = MenuFragmentDirections.actionMenuFragmentToPromoFragment()
        homeAuthController?.navigate(direction)
    }
}