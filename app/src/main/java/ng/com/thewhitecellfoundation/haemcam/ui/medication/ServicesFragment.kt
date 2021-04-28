package ng.com.thewhitecellfoundation.haemcam.ui.medication

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ng.com.thewhitecellfoundation.common.utils.viewBinding
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentServicesBinding
import ng.com.thewhitecellfoundation.navigation.navigator.extensions.navigator

/**
 * A simple [Fragment] subclass.
 * Use the [ServicesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ServicesFragment : Fragment(R.layout.fragment_services) {
    private val binding by viewBinding(FragmentServicesBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.chemotherapyBtn.setOnClickListener {
            navigator.goto(R.id.chemoTherapyFragment)
        }
    }
}
