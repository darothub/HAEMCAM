package ng.com.thewhitecellfoundation.haemcam.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import ng.com.thewhitecellfoundation.common.extensions.setPartialSpan
import ng.com.thewhitecellfoundation.common.utils.viewBinding
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentMobileVerificationBinding
import ng.com.thewhitecellfoundation.navigation.navigator.extensions.navigator

/**
 * A simple [Fragment] subclass.
 * Use the [MobileVerificationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MobileVerificationFragment : MainBaseFragment(R.layout.fragment_mobile_verification) {
    private val binding by viewBinding(FragmentMobileVerificationBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.termAndConditionTv.setPartialSpan(
            Pair(
                "Terms of use",
                {
                    Toast.makeText(requireContext(), "Terms of use Clicked", Toast.LENGTH_SHORT).show()
                }
            ),
            Pair(
                "Privacy Policy",
                {
                    Toast.makeText(requireContext(), "Privacy Policy Clicked", Toast.LENGTH_SHORT).show()
                }
            )
        )

        binding.btnPbar.onClickActionListener {
            navigator.goto(R.id.codeVerificationFragment)
        }
    }
}
