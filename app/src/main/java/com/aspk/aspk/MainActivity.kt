  package com.aspk.aspk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.aspk.aspk.databinding.ActivityMainBinding
import com.aspk.aspk.ui.auth.ContainerAuthFragmentDirections
import com.aspk.aspk.util.SessionManagement

  class MainActivity : AppCompatActivity() {

      lateinit var sessionManagement: SessionManagement
      lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sessionManagement = SessionManagement(this)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_main) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.nav_main)

        if (!sessionManagement.isFirst){
            if (sessionManagement.isLoggedIn){
                graph.setStartDestination(R.id.containerHomeFragment)
            } else {
                graph.setStartDestination(R.id.containerAuthFragment)
            }
        }
        val navController = navHostFragment.navController
        navController.setGraph(graph, intent.extras)

    }


}