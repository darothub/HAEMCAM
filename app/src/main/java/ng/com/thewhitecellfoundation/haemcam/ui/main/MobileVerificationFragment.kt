package ng.com.thewhitecellfoundation.haemcam.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import ng.com.thewhitecellfoundation.common.string.setPartialSpan
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentMobileVerificationBinding
import ng.com.thewhitecellfoundation.navigation.navigator.Navigator

/**
 * A simple [Fragment] subclass.
 * Use the [MobileVerificationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MobileVerificationFragment : Fragment() {
    private var _binding: FragmentMobileVerificationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        _binding = FragmentMobileVerificationBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

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
        binding.btnPbar.btn.apply {
            text = getString(R.string.send_code)
            setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        }
        binding.btnPbar.btn.setOnClickListener {
            (requireActivity() as Navigator).goto(R.id.codeVerificationFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
