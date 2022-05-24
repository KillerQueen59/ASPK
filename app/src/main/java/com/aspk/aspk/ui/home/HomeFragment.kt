package com.aspk.aspk.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.aspk.aspk.R
import com.aspk.aspk.databinding.FragmentHomeBinding
import com.aspk.aspk.ui.auth.JoinNowFragmentDirections
import com.aspk.aspk.util.SessionManagement
import splitties.toast.toast

class HomeFragment: Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var sessionManagement: SessionManagement
    private val homeAuthController: NavController? by lazy { activity?.findNavController(R.id.fcv_home) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sessionManagement = SessionManagement(requireActivity())
        binding.textView2.text = "Welcome, ${sessionManagement.name}"
        handleClick()
    }

    private fun handleClick(){
        with(binding){
            menu.setOnClickListener {
                goToMenu()
            }
            analysis.setOnClickListener {
                goToAnalysis()
            }
        }
    }

    private fun goToMenu(){
        val direction = HomeFragmentDirections.actionHomeFragmentToMenuFragment()
        homeAuthController?.navigate(direction)
    }

    private fun goToAnalysis(){
        val direction = HomeFragmentDirections.actionHomeFragmentToAnalysisFragment()
        homeAuthController?.navigate(direction)
    }

}