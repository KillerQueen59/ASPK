package com.aspk.aspk.ui.onboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.aspk.aspk.R
import com.aspk.aspk.databinding.FragmentOnboard3Binding

class Onboard3Fragment: Fragment() {

    lateinit var binding: FragmentOnboard3Binding
    private val containerNavController: NavController? by lazy { activity?.findNavController(R.id.nav_host_fragment_main) }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboard3Binding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleClick()
    }

    private fun handleClick(){
        with(binding){
            btnStart.setOnClickListener {
                goToAuth()
            }
            btnBack.setOnClickListener {
                activity?.onBackPressed()
            }
        }
    }

    private fun goToAuth(){
        val direction = ContainerOnboardFragmentDirections.actionContainerOnboardFragmentToContainerAuthFragment()
        containerNavController?.navigate(direction)
    }

}