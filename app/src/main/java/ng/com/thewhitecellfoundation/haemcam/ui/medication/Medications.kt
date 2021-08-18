package ng.com.thewhitecellfoundation.haemcam.ui.medication

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ng.com.thewhitecellfoundation.common.utils.viewBinding
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentMedicationsBinding
import ng.com.thewhitecellfoundation.haemcam.ui.home.HomeBaseFragment
import ng.com.thewhitecellfoundation.navigation.navigator.extensions.navigator

/**
 * A simple [Fragment] subclass.
 * Use the [Medications.newInstance] factory method to
 * create an instance of this fragment.
 */
class Medications : HomeBaseFragment(R.layout.fragment_medications) {
    private val binding by viewBinding(FragmentMedicationsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bookAppointmentBtn.setOnClickListener {
            navigator.goto(R.id.servicesFragment)
        }
    }
}
