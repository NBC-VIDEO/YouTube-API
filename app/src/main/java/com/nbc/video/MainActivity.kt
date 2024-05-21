package com.nbc.video

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import com.nbc.video.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val navFragment = supportFragmentManager.findFragmentById(R.id.fr_navigation) as NavHostFragment
        var navController = navFragment.navController
        var destination = navController.currentDestination?.id

        binding.apply {
            includeMainNavigation.mainNavHome.setOnClickListener {
                if (destination == R.id.searchFragment) {
                    navController.navigate(R.id.action_searchFragment_to_homeFragment)
                    navController = navFragment.navController
                    destination = navController.currentDestination?.id
                }
                if (destination == R.id.myVideoFragment) {
                    navController.navigate(R.id.action_myVideoFragment_to_homeFragment)
                    navController = navFragment.navController
                    destination = navController.currentDestination?.id
                }
            }
            includeMainNavigation.mainNavSearch.setOnClickListener {
                if (destination == R.id.homeFragment) {
                    navController.navigate(R.id.action_homeFragment_to_searchFragment)
                    navController = navFragment.navController
                    destination = navController.currentDestination?.id
                }
                if (destination == R.id.myVideoFragment) {
                    navController.navigate(R.id.action_myVideoFragment_to_searchFragment)
                    navController = navFragment.navController
                    destination = navController.currentDestination?.id
                }
            }
            includeMainNavigation.mainNavFavorite.setOnClickListener {
                if (destination == R.id.homeFragment) {
                    navController.navigate(R.id.action_homeFragment_to_myVideoFragment)
                    navController = navFragment.navController
                    destination = navController.currentDestination?.id
                }
                if (destination == R.id.searchFragment) {
                    navController.navigate(R.id.action_searchFragment_to_myVideoFragment)
                    navController = navFragment.navController
                    destination = navController.currentDestination?.id
                }
            }
        }
    }
}
