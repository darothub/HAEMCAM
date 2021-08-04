package ng.com.thewhitecellfoundation.haemcam.ui.home

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ng.com.thewhitecellfoundation.common.utils.viewBinding
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.data.FeedbackDummyDataGenerator
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentFeedbackBinding
import ng.com.thewhitecellfoundation.haemcam.ui.adapter.FeedBackAdapter

/**
 * A simple [Fragment] subclass.
 * Use the [FeedbackFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class FeedbackFragment : Fragment(R.layout.fragment_feedback) {

    private val feedBackAdapter by lazy {
        FeedBackAdapter()
    }
    private val binding by viewBinding(FragmentFeedbackBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerview()
    }

    override fun onResume() {
        super.onResume()

        // initialising the Diseases dropdown menu
        val diseases = resources.getStringArray(R.array.feedback_items)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.fragment_feedback_drop_down_item, diseases)
        binding.fragmentFeedbackDropdownAutoCompleteTextView.setAdapter(arrayAdapter)
    }

    /**
     * Setup the recyclerview with a [LinearLayoutManager]
     * a [FeedbackDummyDataGenerator] is used to temporary populate the recyclerview
     */
    private fun setUpRecyclerview() = with(binding) {
        fragmentFeedbackRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        feedBackAdapter.setData(FeedbackDummyDataGenerator.createFeedbackData())
        fragmentFeedbackRecyclerview.adapter = feedBackAdapter
    }
}
