package ng.com.thewhitecellfoundation.haemcam.ui.home

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import ng.com.thewhitecellfoundation.common.utils.viewBinding
import ng.com.thewhitecellfoundation.common.views.hide
import ng.com.thewhitecellfoundation.common.views.show
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.ActivityHomeBinding
import ng.com.thewhitecellfoundation.navigation.navigator.Navigator
import ng.com.thewhitecellfoundation.navigation.navigator.extensions.navigator

class HomeActivity : AppCompatActivity(), Navigator {
    private val binding by viewBinding(ActivityHomeBinding::inflate)
    override lateinit var navHostFragment: NavHostFragment
    override lateinit var navController: NavController

    private val navListener =
        NavController.OnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.userInfoFragment -> {
                    binding.bottomNav.hide()
                    binding.appbar.userGreetingsTv.show()
                    binding.appbar.titleTv.hide()
                    binding.appbar.userImageIv.setImageResource(R.drawable.ic_person_24)
                }
                R.id.userTreatmentFragment -> {
                    binding.bottomNav.hide()
                    binding.appbar.userGreetingsTv.show()
                    binding.appbar.titleTv.hide()
                    binding.appbar.userImageIv.setImageResource(R.drawable.ic_person_24)
                }
                R.id.homeFragment -> {
                    binding.bottomNav.show()
                    binding.appbar.helpTv.setImageResource(R.drawable.ic_settings_icon)
                    binding.appbar.userImageIv.setImageResource(R.drawable.ic_person_24)
                    binding.appbar.userGreetingsTv.show()
                    binding.appbar.titleTv.hide()
                }
                R.id.medicationsFragment -> {
                    binding.bottomNav.show()
                    binding.appbar.helpTv.setImageResource(R.drawable.ic_settings_icon)
                    binding.appbar.userGreetingsTv.hide()
                    binding.appbar.userImageIv.setImageResource(R.drawable.ic_baseline_keyboard_backspace_24)
                    binding.appbar.titleTv.text = getString(R.string.medication)
                    binding.appbar.titleTv.show()
                    binding.appbar.userImageIv.setOnClickListener {
                        navigator.navController.popBackStack()
                    }
                }
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = binding.root
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
}
