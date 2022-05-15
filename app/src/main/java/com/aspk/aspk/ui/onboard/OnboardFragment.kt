package com.aspk.aspk.ui.onboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.aspk.aspk.R
import com.aspk.aspk.databinding.FragmentOnboardBinding

class OnboardFragment: Fragment() {

    lateinit var binding: FragmentOnboardBinding
    private val onboardNavController: NavController? by lazy { activity?.findNavController(R.id.fcv_onboard) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleClick()
    }

    private fun handleClick(){
        with(binding){
            btnGo.setOnClickListener {
                goToOnboard2()
            }
        }
    }


    private fun goToOnboard2(){
        val direction = OnboardFragmentDirections.actionOnboardFragmentToOnboard2Fragment2()
        onboardNavController?.navigate(direction)
    }
}