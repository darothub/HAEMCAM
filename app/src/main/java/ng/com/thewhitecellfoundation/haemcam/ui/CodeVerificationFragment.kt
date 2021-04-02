package ng.com.thewhitecellfoundation.haemcam.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentCodeVerificationBinding
import ng.com.thewhitecellfoundation.navigation.navigator.Navigator
import ng.com.thewhitecellfoundation.utils.string.setPartialSpan

/**
 * A simple [Fragment] subclass.
 * Use the [CodeVerificationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CodeVerificationFragment : Fragment() {
    private var _binding: FragmentCodeVerificationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCodeVerificationBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

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
        binding.btnPbar.btn.apply {
            text = getString(R.string.continue_str)
            setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        }
        binding.btnPbar.btn.setOnClickListener {
            (requireActivity() as Navigator).goto(R.id.welcomeFragment)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
