package ng.com.thewhitecellfoundation.haemcam.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import ng.com.thewhitecellfoundation.common.utils.viewBinding
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentFeedbackBinding
import ng.com.thewhitecellfoundation.haemcam.model.Feedback
import ng.com.thewhitecellfoundation.haemcam.ui.adapter.feedbackModel

/**
 * A simple [Fragment] subclass.
 * Use the [FeedbackFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FeedbackFragment : Fragment(R.layout.fragment_feedback) {

    private val binding by viewBinding(FragmentFeedbackBinding::bind)

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categories = resources.getStringArray(R.array.categories)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.fragment_feedback_dropdown_item, categories)
        binding.fragmentFeedbackAutocompleteTextview.setAdapter(arrayAdapter)

        val name = "Dr Anthonia Peterson"
        val message = "Here are some medical feedback.... " + "Here are some medical feedback....Here are some medical feedback....  " + " Here are some medical feedback...."
        val likeText = "Likes"
        val comment = "Comments"

        val listOfFeedback = arrayListOf(
            Feedback(1, name, message, 1, 1, likeText, 1, 1, comment),
            Feedback(2, name, message, 1, 2, likeText, 1, 2, comment),
            Feedback(3, name, message, 1, 3, likeText, 1, 3, comment),
            Feedback(4, name, message, 1, 4, likeText, 1, 4, comment),
            Feedback(5, name, message, 1, 5, likeText, 1, 5, comment),
            Feedback(6, name, message, 1, 6, likeText, 1, 6, comment),
            Feedback(7, name, message, 1, 7, likeText, 1, 7, comment),
            Feedback(8, name, message, 1, 8, likeText, 1, 8, comment),
        )

        binding.fragmentFeedbackEpoxyRecyclerview.withModels {
            listOfFeedback.forEach { feedback ->
                feedbackModel {
                    id(feedback.id)
                    data(feedback)
                }
            }
        }
    }
}
