package ng.com.thewhitecellfoundation.haemcam.ui.main

import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import hideSystemUI
import kotlinx.coroutines.*
import ng.com.thewhitecellfoundation.common.databinding.ReusableToolbarBinding
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.ActivityMainBinding
import ng.com.thewhitecellfoundation.navigation.navigator.Navigator

class MainActivity : AppCompatActivity(), Navigator {
    override lateinit var navHostFragment: NavHostFragment
    override lateinit var navController: NavController
    lateinit var binding: ActivityMainBinding

    lateinit var reusableToolbarBinding: ReusableToolbarBinding

    private val navListener =
        NavController.OnDestinationChangedListener { _, destination, _ ->
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

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        reusableToolbarBinding = binding.toolbar

        setContentView(view)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        navController = findNavController(R.id.fragment)
    }

    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(navListener)
    }

    override fun onPause() {
        super.onPause()
        navController.removeOnDestinationChangedListener(navListener)
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

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        if (hasFocus) hideSystemUI()
    }
}

fun main() {
}
