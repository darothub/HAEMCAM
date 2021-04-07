package ng.com.thewhitecellfoundation.haemcam.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import ng.com.thewhitecellfoundation.common.utils.CustomEditText
import ng.com.thewhitecellfoundation.common.utils.Validation
import ng.com.thewhitecellfoundation.common.views.customOnTouchListener
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentLoginBinding
import ng.com.thewhitecellfoundation.navigation.navigator.Navigator

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
        binding.btnPbar.btn.apply {
            text = getString(R.string.login_str)
            setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        }
        binding.signupTv.setOnClickListener {
            (requireActivity() as Navigator).goto(R.id.createAccountFragment)
        }

        binding.btnPbar.btn.setOnClickListener {
            val a = arrayOf(binding.emailAddressEt, binding.passwordEt).map {
                val pair = Pair<CustomEditText, TextInputEditText?>(
                    CustomEditText(it.text.toString(), it.tag.toString(), it.error), it
                )
                pair
            }
            val res = Validation.Builder()
            if (binding.loginVf[0].isShown) {
                res.separateFieldByTag(a)
                    .email()
                    .password()
                    .build()
                Toast.makeText(requireContext(), "Login ${res?.respond?.tag}", Toast.LENGTH_SHORT).show()
                Log.i("Login", "Login ${res?.respond?.tag}")
            } else {
                res
                    .separateFieldByTag(a)
                    .phone()
                    .password()
                    .build()
                Toast.makeText(requireContext(), "Login ${res?.respond?.tag}", Toast.LENGTH_SHORT).show()
                Log.i("Login", "Login ${res?.respond?.tag}")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
