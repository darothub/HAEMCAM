package ng.com.thewhitecellfoundation.haemcam.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import ng.com.thewhitecellfoundation.common.extensions.setPartialSpan
import ng.com.thewhitecellfoundation.common.utils.viewBinding
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentCodeVerificationBinding
import ng.com.thewhitecellfoundation.navigation.navigator.Navigator

/**
 * A simple [Fragment] subclass.
 * Use the [CodeVerificationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CodeVerificationFragment : MainBaseFragment(R.layout.fragment_code_verification) {
    private val binding by viewBinding(FragmentCodeVerificationBinding::bind)
    override val backgroundColor: Int
        get() = R.color.primaryColor
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.didntGetCodeTv.setPartialSpan(
            Pair(
                "Resend Code",
                {
                    Toast.makeText(requireContext(), "Resend", Toast.LENGTH_SHORT).show()
                }
            )
        )

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
            (requireActivity() as Navigator).goto(R.id.welcomeFragment)
        }
    }
}
