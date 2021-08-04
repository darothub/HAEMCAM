package ng.com.thewhitecellfoundation.haemcam.ui.home

import android.net.Uri
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import ng.com.thewhitecellfoundation.common.extensions.hide
import ng.com.thewhitecellfoundation.common.extensions.show
import ng.com.thewhitecellfoundation.common.utils.viewBinding
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.ActivityHomeBinding
import ng.com.thewhitecellfoundation.navigation.navigator.Navigator
import ng.com.thewhitecellfoundation.navigation.navigator.extensions.navigator

class HomeActivity : AppCompatActivity(), Navigator, ButtonAndProgressBarState, NavController.OnDestinationChangedListener {
    private val binding by viewBinding(ActivityHomeBinding::inflate)

    override lateinit var navHostFragment: NavHostFragment
    override lateinit var navController: NavController

    private fun setUpScreenWithGreeting(@DrawableRes rightImage: Int) {
        binding.bottomNav.hide()
        binding.appbar.rightImage = ContextCompat.getDrawable(this, rightImage)
        binding.appbar.leftImage = ContextCompat.getDrawable(this, R.drawable.ic_person_24)
        binding.appbar.toolBarTitle = null
        binding.appbar.greetingText = getString(R.string.hi, "Darot")
    }

    private fun setUpScreenWithoutGreeting(name: String) {
        binding.bottomNav.show()
        binding.appbar.rightImage = ContextCompat.getDrawable(this, R.drawable.ic_settings_icon)
        binding.appbar.leftImage = ContextCompat.getDrawable(this, R.drawable.ic_baseline_keyboard_backspace_24)
        binding.appbar.toolBarTitle = name
        binding.appbar.greetingText = null
        binding.appbar.leftImageClickListener {
            navigator.navController.popBackStack()
            Unit
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        navController = findNavController(R.id.fragment)

        binding.bottomNav.setupWithNavController(navController)
    }

    override fun onStart() {
        super.onStart()
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

    override fun showView() {
        binding.btnPbar.show()
    }
    override fun hideView() {
        binding.btnPbar.hide()
    }

    override fun buttonState(buttonText: String?, loading: Boolean, onclick: (() -> Unit?)?) {
        if (buttonText.isNullOrEmpty()) {
            binding.btnPbar.hide()
        } else {
            with(binding.btnPbar) {
                show()
                this.loading = loading
                this.buttonText = buttonText
                onClickActionListener { onclick?.invoke() }
            }
        }
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        when (destination.id) {
            R.id.userDiagnosisInfoFragment -> {
                setUpScreenWithGreeting(R.drawable.ic_primary_help_24)
            }
            R.id.regimenAndDrugsFragment -> {
                setUpScreenWithGreeting(R.drawable.ic_primary_help_24)
            }
            R.id.homeFragment -> {
                binding.bottomNav.show()
                setUpScreenWithGreeting(R.drawable.ic_settings_icon)
            }
            R.id.medicationsFragment, R.id.chemoTherapyFragment, R.id.otherDrugFragment,
            R.id.servicesFragment, R.id.sideEffectReportingFragment -> {
                setUpScreenWithoutGreeting(getString(R.string.medication))
            }

            R.id.nutritionMenuFragment, R.id.recipesFragment -> {
                setUpScreenWithoutGreeting(getString(R.string.nutrition))
            }
        }
    }
}

interface ButtonAndProgressBarState {
    fun showView() {}
    fun hideView() {}
    fun buttonState(buttonText: String? = "", loading: Boolean = false, onclick: (() -> Unit?)? = { })
    fun setProgressBarTint() {}
}
