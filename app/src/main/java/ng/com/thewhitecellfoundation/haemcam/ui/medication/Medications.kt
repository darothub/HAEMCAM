package ng.com.thewhitecellfoundation.haemcam.ui.medication

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ng.com.thewhitecellfoundation.common.extensions.changeBackgroundColor
import ng.com.thewhitecellfoundation.common.utils.viewBinding
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentMedicationsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Medications.newInstance] factory method to
 * create an instance of this fragment.
 */
class Medications : Fragment(R.layout.fragment_medications) {
    private val binding by viewBinding(FragmentMedicationsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bookAppointmentBtn.apply {
            background.changeBackgroundColor(requireContext(), R.color.primaryColor)
        }
    }
}
