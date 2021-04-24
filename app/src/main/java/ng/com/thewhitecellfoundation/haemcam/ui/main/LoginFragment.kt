package ng.com.thewhitecellfoundation.haemcam.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import ng.com.thewhitecellfoundation.common.fragment.navigator
import ng.com.thewhitecellfoundation.common.utils.CustomEditTextField
import ng.com.thewhitecellfoundation.common.utils.Validation
import ng.com.thewhitecellfoundation.common.utils.viewBinding
import ng.com.thewhitecellfoundation.common.views.customOnDrawableRightListener
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentLoginBinding

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment(R.layout.fragment_login) {
    private val binding by viewBinding(FragmentLoginBinding::bind)

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.emailAddressEt.customOnDrawableRightListener {
            binding.loginVf.showNext()
        }

        binding.loginPhoneNumberEt.customOnDrawableRightListener {
            binding.loginVf.showNext()
        }
        binding.btnPbar.btn.apply {
            text = getString(R.string.login_str)
            setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        }
        binding.signupTv.setOnClickListener {
            navigator.goto(R.id.createAccountFragment)
        }

        binding.btnPbar.btn.setOnClickListener {
            val a = arrayOf(binding.emailAddressEt, binding.passwordEt).map {
                val pair = Pair<CustomEditTextField, TextInputEditText?>(
                    CustomEditTextField(it.text.toString(), it.tag.toString()), it
                )
                pair
            }
            val res = Validation.Builder()
            if (binding.loginVf[0].isShown) {
                res.separateFieldByTag(a)
                    .email()
                    .password()
                    .build()
                Toast.makeText(requireContext(), "Login ${res.respond?.first?.tag}", Toast.LENGTH_SHORT).show()
                Log.i("Login", "Login ${res.respond?.first?.tag}")
            } else {
                val a = arrayOf(binding.loginPhoneNumberEt, binding.passwordEt).map {
                    val pair = Pair<CustomEditTextField, TextInputEditText?>(
                        CustomEditTextField(it.text.toString(), it.tag.toString()), it
                    )
                    pair
                }
                res.separateFieldByTag(a)
                    .phone()
                    .password()
                    .build()
                Toast.makeText(requireContext(), "Login ${res.respond?.first?.tag}", Toast.LENGTH_SHORT).show()
                Log.i("Login", "Login ${res.respond?.first?.tag}")
            }
        }
    }
}
