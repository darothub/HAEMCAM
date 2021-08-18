package ng.com.thewhitecellfoundation.haemcam.ui.home

import android.content.Context
import androidx.fragment.app.Fragment
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.ui.diagnosis.RegimenAndDrugsFragment
import ng.com.thewhitecellfoundation.haemcam.ui.diagnosis.UserDiagnosisInfoFragment
import ng.com.thewhitecellfoundation.haemcam.ui.lab.LabResultsFragment
import ng.com.thewhitecellfoundation.haemcam.ui.medication.ChemoTherapyInfo
import ng.com.thewhitecellfoundation.haemcam.ui.medication.OtherDrugInfoFragment
import ng.com.thewhitecellfoundation.haemcam.ui.medication.ServicesFragment
import ng.com.thewhitecellfoundation.haemcam.ui.nutrition.NutritionMenuFragment
import ng.com.thewhitecellfoundation.haemcam.ui.nutrition.RecipesFragment
import ng.com.thewhitecellfoundation.navigation.navigator.extensions.navigator

abstract class HomeBaseFragment(layout: Int) : Fragment(layout) {
    open val buttonAndProgressBarState: ButtonAndProgressBarState by lazy {
        requireActivity() as ButtonAndProgressBarState
    }
    open val bottomNavigationVisibility: BottomNavigationVisibility by lazy {
        requireActivity() as BottomNavigationVisibility
    }
    open val toolBarSetup: ToolBarSetup by lazy {
        requireActivity() as ToolBarSetup
    }
    open var showBottomNavigation = true

    open var toolRightImageDrawable: Int? = R.drawable.ic_primary_help_24
    open var toolLeftImageDrawable: Int? = R.drawable.ic_person_24
    open var toolBarGreetingText: String? = null
    open var toolBarTitle: String? = null
    open var leftAction: () -> Unit = {}

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setEachFragmentToolBar()
    }

    override fun onStart() {
        super.onStart()
        if (showBottomNavigation) bottomNavigationVisibility.showBottomNavigationView()
        else bottomNavigationVisibility.hideBottomNavigationView()
        buttonAndProgressBarState.buttonState("")
        with(toolBarSetup) {
            toolRightImageDrawable?.let { setRightImage(it) }
            toolLeftImageDrawable?.let { setLeftImage(it, leftAction) }
            setGreetingText(toolBarGreetingText)
            setToolBarTitle(title = toolBarTitle)
        }
    }

    fun setEachFragmentToolBar() {
        when (this) {
            is HomeFragment -> {
                toolRightImageDrawable = R.drawable.ic_settings_icon
                setToolBarWithOutTitle()
            }
            is UserDiagnosisInfoFragment, is RegimenAndDrugsFragment -> {
                showBottomNavigation = false
                setToolBarWithOutTitle()
            }
            is NotificationFragment -> {
                setToolBarWithTitle(getString(R.string.notification))
            }
            is ReportFragment -> {
                setToolBarWithTitle(getString(R.string.report))
            }
            is FeedbackFragment -> {
                setToolBarWithTitle(getString(R.string.feed_back))
            }
            is ChemoTherapyInfo, is OtherDrugInfoFragment, is ServicesFragment -> {
                setToolBarWithTitle(getString(R.string.medication))
            }
            is RecipesFragment, is NutritionMenuFragment -> {
                setToolBarWithTitle(getString(R.string.nutrition))
            }
            is LabResultsFragment -> {
                setToolBarWithTitle(getString(R.string.lab_result))
            }
        }
    }
    private fun setToolBarWithTitle(title: String) {
        toolLeftImageDrawable = R.drawable.ic_baseline_keyboard_backspace_24
        leftAction = {
            navigator.navController.popBackStack()
        }
        toolRightImageDrawable = R.drawable.ic_settings_icon
        toolBarTitle = title
    }
    private fun setToolBarWithOutTitle() {
        toolBarGreetingText = getString(R.string.hi, "User")
        toolBarTitle = "null"
    }
    override fun onPause() {
        super.onPause()
        // Remove button
        buttonAndProgressBarState.buttonState("")
        with(toolBarSetup) {
            setGreetingText("null")
            setToolBarTitle(title = "null")
        }
    }
}
