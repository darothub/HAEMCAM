package ng.com.thewhitecellfoundation.haemcam.ui.home

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import ng.com.thewhitecellfoundation.common.extensions.hide
import ng.com.thewhitecellfoundation.common.extensions.show
import ng.com.thewhitecellfoundation.common.utils.viewBinding
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.ActivityHomeBinding
import ng.com.thewhitecellfoundation.navigation.navigator.Navigator
import ng.com.thewhitecellfoundation.navigation.navigator.extensions.navigator

class HomeActivity : AppCompatActivity(), Navigator, ButtonAndProgressBarState {
    private val binding by viewBinding(ActivityHomeBinding::inflate)

    override lateinit var navHostFragment: NavHostFragment
    override lateinit var navController: NavController
    lateinit var behavior: BottomSheetBehavior<ConstraintLayout>

    private val navListener =
        NavController.OnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.userDiagnosisInfoFragment -> {
                    helpToolBarViews()
                }
                R.id.userTreatmentFragment -> {
                    helpToolBarViews()
                }
                R.id.bloodGroupSelectionFragment -> {
                    helpToolBarViews()
                }
                R.id.homeFragment -> {
                    binding.bottomNav.show()
                    binding.appbar.helpTv.setImageResource(R.drawable.ic_settings_icon)
                    binding.appbar.userImageIv.setImageResource(R.drawable.ic_person_24)
                    binding.appbar.userGreetingsTv.show()
                    binding.appbar.titleTv.hide()
                }
                R.id.medicationsFragment, R.id.chemoTherapyFragment, R.id.otherDrugFragment,
                R.id.servicesFragment, R.id.sideEffectReportingFragment -> {
                    settingsToolBarViews(getString(R.string.medication))
                }

                R.id.nutritionMenuFragment, R.id.recipesFragment -> {
                    settingsToolBarViews(getString(R.string.nutrition))
                }
            }
        }

    private fun helpToolBarViews() {
        binding.bottomNav.hide()
        binding.appbar.userGreetingsTv.show()
        binding.appbar.titleTv.hide()
        binding.appbar.helpTv.setImageResource(R.drawable.ic_primary_help_24)
        binding.appbar.userImageIv.setImageResource(R.drawable.ic_person_24)
    }

    private fun settingsToolBarViews(name: String) {
        binding.bottomNav.show()
        binding.appbar.helpTv.setImageResource(R.drawable.ic_settings_icon)
        binding.appbar.userGreetingsTv.hide()
        binding.appbar.userImageIv.setImageResource(R.drawable.ic_baseline_keyboard_backspace_24)
        binding.appbar.titleTv.text = name
        binding.appbar.titleTv.show()
        binding.appbar.userImageIv.setOnClickListener {
            navigator.navController.popBackStack()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        navController = findNavController(R.id.fragment)
    }

    override fun onStart() {
        super.onStart()
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

    override fun buttonState(buttonText: String?, visible: Boolean, action: (() -> Unit?)?) {
        if (visible) binding.btnPbar.btn.show() else binding.btnPbar.btn.hide()
        binding.btnPbar.btn.text = buttonText
        binding.btnPbar.btn.setOnClickListener {
            action?.invoke()
        }
    }

    override fun progressBarState(visible: Boolean) {}
}

interface ButtonAndProgressBarState {
    fun buttonState(buttonText: String? = "", visible: Boolean = true, action: (() -> Unit?)? = { })
    fun progressBarState(visible: Boolean)
}
