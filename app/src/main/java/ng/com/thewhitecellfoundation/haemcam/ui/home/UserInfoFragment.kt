package ng.com.thewhitecellfoundation.haemcam.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ng.com.thewhitecellfoundation.common.fragment.navigator
import ng.com.thewhitecellfoundation.common.fragment.onBackDispatcher
import ng.com.thewhitecellfoundation.common.utils.viewBinding
import ng.com.thewhitecellfoundation.common.views.dismissPowerViewDropDown
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentUserInfoBinding
import ng.com.thewhitecellfoundation.navigation.navigator.Navigator

/**
 * A simple [Fragment] subclass.
 * Use the [UserInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserInfoFragment : Fragment(R.layout.fragment_user_info) {

    private val binding by viewBinding(FragmentUserInfoBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onBackDispatcher {
            navigator.navController.popBackStack()
        }

        binding.nextBtn.setOnClickListener {
            (requireActivity() as Navigator).goto(R.id.userTreatmentFragment)
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
