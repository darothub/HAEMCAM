package ng.com.thewhitecellfoundation.haemcam.ui.home

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import ng.com.thewhitecellfoundation.common.utils.viewBinding
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentFeedbackBinding

/**
 * A simple [Fragment] subclass.
 * Use the [FeedbackFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FeedbackFragment : Fragment(R.layout.fragment_feedback) {

    private lateinit var sicknessSelectorDropdown: AutoCompleteTextView
    private val binding by viewBinding(FragmentFeedbackBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sicknessSelectorDropdown = binding.categoryDropdownEditText
    }

    override fun onResume() {
        super.onResume()
        /*Set up States Dropdown*/
        val states = resources.getStringArray(R.array.sickness)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.categorries_drop_down_item, states)
        sicknessSelectorDropdown.setAdapter(arrayAdapter)
    }
}
