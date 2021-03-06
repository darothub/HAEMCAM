package ng.com.thewhitecellfoundation.haemcam.ui.diagnosis

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ng.com.thewhitecellfoundation.common.extensions.dismissPowerViewDropDown
import ng.com.thewhitecellfoundation.common.extensions.onBackDispatcher
import ng.com.thewhitecellfoundation.common.utils.viewBinding
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentUserDiagnosisInfoBinding
import ng.com.thewhitecellfoundation.haemcam.ui.home.HomeBaseFragment
import ng.com.thewhitecellfoundation.navigation.navigator.extensions.navigator

/**
 * A simple [Fragment] subclass.
 * Use the [UserDiagnosisInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserDiagnosisInfoFragment : HomeBaseFragment(R.layout.fragment_user_diagnosis_info) {
    val binding by viewBinding(FragmentUserDiagnosisInfoBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onBackDispatcher {
            navigator.navController.popBackStack()
        }
    }

    override fun onStart() {
        super.onStart()
        buttonAndProgressBarState.buttonState("Next") {
            navigator.goto(R.id.regimenAndDrugsFragment)
        }
    }

    override fun onPause() {
        super.onPause()
        dismissPowerViewDropDown(
            binding.genderSpinner,
            binding.diagnosisSpinner
        )
    }
}
