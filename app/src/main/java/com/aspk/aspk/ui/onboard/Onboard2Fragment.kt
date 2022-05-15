package com.aspk.aspk.ui.onboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.aspk.aspk.R
import com.aspk.aspk.databinding.FragmentOnboard2Binding

class Onboard2Fragment: Fragment() {

    lateinit var binding: FragmentOnboard2Binding
    private val onboardNavController: NavController? by lazy { activity?.findNavController(R.id.fcv_onboard) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboard2Binding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleClick()
    }

    private fun handleClick(){
        with(binding){
            btnNext.setOnClickListener {
                goToOnboard3()
            }
            btnBack.setOnClickListener {
                activity?.onBackPressed()
            }
        }
    }

    private fun goToOnboard3(){
        val direction = Onboard2FragmentDirections.actionOnboard2Fragment2ToOnboard3Fragment()
        onboardNavController?.navigate(direction)
    }

}