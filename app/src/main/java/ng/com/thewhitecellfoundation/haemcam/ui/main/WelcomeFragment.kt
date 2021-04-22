package ng.com.thewhitecellfoundation.haemcam.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ng.com.thewhitecellfoundation.common.drawable.changeBackgroundColor
import ng.com.thewhitecellfoundation.common.utils.viewBinding
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentWelcomeBinding
import ng.com.thewhitecellfoundation.haemcam.ui.home.HomeActivity

/**
 * A simple [Fragment] subclass.
 * Use the [WelcomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WelcomeFragment : Fragment(R.layout.fragment_welcome) {
    private val binding by viewBinding(FragmentWelcomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.continueBtn.apply {
            background.changeBackgroundColor(requireContext(), R.color.primaryColor)
        }
        binding.continueBtn.setOnClickListener {
            startActivity(Intent(requireActivity(), HomeActivity::class.java))
            requireActivity().finish()
        }
    }
}
