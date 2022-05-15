package com.aspk.aspk.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.aspk.aspk.R
import com.aspk.aspk.databinding.FragmentJoinNowBinding
import com.aspk.aspk.databinding.FragmentOnboard2Binding

class JoinNowFragment: Fragment() {

    lateinit var binding: FragmentJoinNowBinding
    private val authNavController: NavController? by lazy { activity?.findNavController(R.id.fcv_auth) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentJoinNowBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleClick()
    }

    private fun handleClick(){
        with(binding){
            btnJoin.setOnClickListener {
                goToAuth()
            }
        }
    }

    private fun goToAuth(){
        val direction = JoinNowFragmentDirections.actionJoinNowFragmentToAuthFragment()
        authNavController?.navigate(direction)
    }
}