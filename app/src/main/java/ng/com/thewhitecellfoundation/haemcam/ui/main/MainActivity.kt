package ng.com.thewhitecellfoundation.haemcam.ui.main

import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import hideSystemUI
import kotlinx.coroutines.*
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.ActivityMainBinding
import ng.com.thewhitecellfoundation.haemcam.databinding.ReusableAppbarBinding
import ng.com.thewhitecellfoundation.navigation.navigator.Navigator

class MainActivity : AppCompatActivity(), Navigator, NavController.OnDestinationChangedListener {
    override lateinit var navHostFragment: NavHostFragment
    override lateinit var navController: NavController
    lateinit var binding: ActivityMainBinding

    lateinit var reusableAppbarBinding: ReusableAppbarBinding

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        reusableAppbarBinding = binding.toolbar

        setContentView(view)

        hideSystemUI()
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        navController = findNavController(R.id.fragment)
    }

    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(this)
    }

    override fun onPause() {
        super.onPause()
        navController.removeOnDestinationChangedListener(this)
    }

    override fun goto(destination: Int) {
        // Using fragmentId
        navController.navigate(destination)
    }

    override fun goto(uri: Uri) {
        // or using NavDeepLinkRequest
        val request = NavDeepLinkRequest.Builder
            .fromUri(uri)
            .build()
        navController.navigate(request)
    }

    override fun graphSpecificNavigation(graphId: Int) {
        val myNavHostFragment: NavHostFragment = navHostFragment
        val inflater = myNavHostFragment.navController.navInflater
        val graph = inflater.inflate(graphId)
        navController.graph.addAll(graph)
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        when (destination.id) {
            R.id.homeFragment -> {
                binding.toolbar.reusableToolbar.setNavigationOnClickListener {
                    finish()
                }
            }
            R.id.loginFragment -> {
                binding.toolbar.reusableToolbar.setNavigationOnClickListener {
                    navController.popBackStack()
                }
            }
            R.id.createAccountFragment -> {
                binding.toolbar.reusableToolbar.setNavigationOnClickListener {
                    navController.popBackStack()
                }
            }
        }
    }
}
