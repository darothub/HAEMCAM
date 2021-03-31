package ng.com.thewhitecellfoundation.haemcam.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentLoginBinding
import ng.com.thewhitecellfoundation.utils.views.customOnTouchListener

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.emailAddressEt.customOnTouchListener {
            binding.loginVf.showNext()
        }

        binding.loginPhoneNumberEt.customOnTouchListener {
            binding.loginVf.showNext()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
