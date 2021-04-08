package ng.com.thewhitecellfoundation.haemcam.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import ng.com.thewhitecellfoundation.common.views.pressedEvent
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentHomeBinding
import ng.com.thewhitecellfoundation.navigation.navigator.Navigator

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signUpBtn.setOnTouchListener { v, event ->
            v as Button
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    pressedEvent(v, R.color.primary_color, getString(R.string.create_an_account))
                }
            }
            false
        }
        binding.loginBtn.setOnTouchListener { v, event ->
            v as Button
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    pressedEvent(v, R.color.primary_color, getString(R.string.login_str))
                }
            }
            false
        }

        binding.loginBtn.setOnClickListener {
            (requireActivity() as Navigator).goto(R.id.loginFragment)
        }
        binding.signUpBtn.setOnClickListener {
            (requireActivity() as Navigator).goto(R.id.createAccountFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
