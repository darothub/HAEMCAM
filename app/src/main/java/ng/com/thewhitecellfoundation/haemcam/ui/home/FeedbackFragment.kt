package ng.com.thewhitecellfoundation.haemcam.ui.home

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ng.com.thewhitecellfoundation.common.utils.viewBinding
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentFeedbackBinding
import ng.com.thewhitecellfoundation.haemcam.model.FeedBackItemData
import ng.com.thewhitecellfoundation.haemcam.ui.adapter.FeedBackAdapter
import ng.com.thewhitecellfoundation.haemcam.ui.adapter.RecyclerClickListener

/**
 * A simple [Fragment] subclass.
 * Use the [FeedbackFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FeedbackFragment : Fragment(R.layout.fragment_feedback), RecyclerClickListener {
    private lateinit var feedBackAdapter: FeedBackAdapter
    private lateinit var sicknessSelectorDropdown: AutoCompleteTextView
    private lateinit var recyclerview: RecyclerView
    private val binding by viewBinding(FragmentFeedbackBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerview = binding.messageRecyclerView
        val listOfFeedbacks = mutableListOf(
            FeedBackItemData(
                "Dr Anthonia Peterson",
                "Here are some medical feeback...Here are some medical feeback...Here are some medical feeback...",
                2, 5
            ),
            FeedBackItemData(
                "Dr Wise ",
                "Here are some medical advice...Here are some medical advice...Here are some medical advice...",
                6, 3
            ),
            FeedBackItemData(
                "Dr Wise ",
                "Here are some medical advice...Here are some medical advice...Here are some medical advice...",
                7, 1
            ),
            FeedBackItemData(
                "Dr Wise ",
                "Here are some medical advice...Here are some medical advice...Here are some medical advice...",
                10, 4
            )
        )
        sicknessSelectorDropdown = binding.categoryDropdownEditText
        feedBackAdapter = FeedBackAdapter(listOfFeedbacks, this)
        recyclerview.adapter = feedBackAdapter
        recyclerview.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun onResume() {
        super.onResume()
        /*Set up States Dropdown*/
        val states = resources.getStringArray(R.array.sickness)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.categorries_drop_down_item, states)
        sicknessSelectorDropdown.setAdapter(arrayAdapter)
    }

    override fun onItemClickToLike(position: Int, photoArrayList: MutableList<FeedBackItemData>) {
        Toast.makeText(requireContext(), "Liked", Toast.LENGTH_SHORT).show()
    }
}
