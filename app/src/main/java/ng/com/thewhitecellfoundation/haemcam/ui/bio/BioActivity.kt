package ng.com.thewhitecellfoundation.haemcam.ui.bio

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.ActivityBioBinding
import ng.com.thewhitecellfoundation.navigation.navigator.Navigator

class BioActivity : AppCompatActivity(), Navigator {
    override lateinit var navHostFragment: NavHostFragment
    override lateinit var navController: NavController
    lateinit var binding: ActivityBioBinding
    private val navListener =
        NavController.OnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBioBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.appbar.reusableToolbar)
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
