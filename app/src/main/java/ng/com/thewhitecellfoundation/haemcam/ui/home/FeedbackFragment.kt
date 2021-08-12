package ng.com.thewhitecellfoundation.haemcam.ui.home

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import ng.com.thewhitecellfoundation.common.utils.viewBinding
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.data.FeedbackDummyDataGenerator
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentFeedbackBinding
import ng.com.thewhitecellfoundation.haemcam.ui.adapter.feedbackModel
import ng.com.thewhitecellfoundation.haemcam.ui.main.BaseFragment

/**
 * A simple [Fragment] subclass.
 * Use the [FeedbackFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class FeedbackFragment : BaseFragment(R.layout.fragment_feedback) {

    private val binding by viewBinding(FragmentFeedbackBinding::bind)
    override val buttonAndProgressBarState: ButtonAndProgressBarState by lazy {
        requireActivity() as ButtonAndProgressBarState
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragmentFeedbackEpoxyRecyclerview.withModels {
            FeedbackDummyDataGenerator.createFeedbackData().forEach { feed ->
                feedbackModel {
                    id(feed.id)
                    data(feed)
                }
            }
        }
    }
    override fun onStart() {
        super.onStart()
        buttonAndProgressBarState.buttonState("")
        val categories = resources.getStringArray(R.array.categories)
        val dropDownAdapter = ArrayAdapter(requireContext(), R.layout.feedback_category_drop_down_item, categories)
        binding.autoComplete.setAdapter(dropDownAdapter)
    }
}
