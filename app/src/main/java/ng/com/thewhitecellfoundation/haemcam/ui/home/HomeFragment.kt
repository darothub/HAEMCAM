package ng.com.thewhitecellfoundation.haemcam.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ng.com.thewhitecellfoundation.common.utils.viewBinding
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentHomeBinding
import ng.com.thewhitecellfoundation.haemcam.model.HomeItemData
import ng.com.thewhitecellfoundation.haemcam.ui.adapter.homeItemView

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listOfHomeItem = arrayListOf(
            HomeItemData(R.drawable.ic_medication_icon, R.string.medication),
            HomeItemData(R.drawable.ic_nutrition_icon, R.string.nutrition),
            HomeItemData(R.drawable.ic_lab_result_icon, R.string.lab_result),
            HomeItemData(R.drawable.ic_transfusion_icon, R.string.transfusion),
        )

        binding.ercv.withModels {
            listOfHomeItem.forEach { hid ->
                homeItemView {
                    id(hid.id)
                    data(hid)
                    spanSizeOverride { totalSpanCount, _, _ -> totalSpanCount / 2 }
                }
            }
        }
    }
}
