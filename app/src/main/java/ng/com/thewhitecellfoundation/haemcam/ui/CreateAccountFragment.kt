package ng.com.thewhitecellfoundation.haemcam.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentCreateAccountBinding
import ng.com.thewhitecellfoundation.navigation.navigator.Navigator
import ng.com.thewhitecellfoundation.utils.databinding.ReusableToolbarBinding
import ng.com.thewhitecellfoundation.utils.string.setPartialSpan

/**
 * A simple [Fragment] subclass.
 * Use the [CreateAccountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateAccountFragment : Fragment() {
    private var _binding: FragmentCreateAccountBinding? = null
    private val binding get() = _binding!!
    private var reusableToolbarBinding: ReusableToolbarBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCreateAccountBinding.inflate(inflater, container, false)
        val view = binding.root
//        reusableToolbarBinding = binding.cafToolbar
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

        reusableToolbarBinding?.reusableToolbars?.setNavigationOnClickListener {
            (requireActivity() as Navigator).navController.popBackStack()
        }

        binding.btnPbar.btn.apply {
            text = getString(R.string.create_an_account)
            setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        }
        binding.btnPbar.btn.setOnClickListener {
            (requireActivity() as Navigator).goto(R.id.mobileVerificationFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
