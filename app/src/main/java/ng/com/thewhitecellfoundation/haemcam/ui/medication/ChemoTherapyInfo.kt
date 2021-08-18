package ng.com.thewhitecellfoundation.haemcam.ui.medication

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ng.com.thewhitecellfoundation.common.utils.viewBinding
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentChemoTherapyInfoBinding
import ng.com.thewhitecellfoundation.haemcam.model.StringItemData
import ng.com.thewhitecellfoundation.haemcam.ui.adapter.chemoTherapyEndView
import ng.com.thewhitecellfoundation.haemcam.ui.adapter.chemoTherapyHeaderView
import ng.com.thewhitecellfoundation.haemcam.ui.adapter.chemoTherapyListView
import ng.com.thewhitecellfoundation.haemcam.ui.home.HomeBaseFragment
import ng.com.thewhitecellfoundation.navigation.navigator.extensions.navigator
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [ChemoTherapyInfo.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChemoTherapyInfo : HomeBaseFragment(R.layout.fragment_chemo_therapy_info) {

    private val binding by viewBinding(FragmentChemoTherapyInfoBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val chemoTestString = arrayListOf(StringItemData("Drug 1"), StringItemData("Drug 2"), StringItemData("Drug 3"), StringItemData("Drug 4"), StringItemData("Drug 5"))
        binding.chemodrugErcv.withModels {
            chemoTestString.forEach { cdt ->
                when (cdt) {
                    chemoTestString.first() -> {
                        chemoTherapyHeaderView {
                            id(cdt.id)
                            data(cdt)
                        }
                    }
                    chemoTestString.last() -> {
                        chemoTherapyEndView {
                            id(cdt.id)
                            data(cdt)
                        }
                    }
                    else -> {
                        chemoTherapyListView {
                            id(cdt.id)
                            data(cdt)
                            onClick { _, _, _, _ ->
                                navigator.goto(R.id.nutritionMenuFragment)
                            }
                        }
                    }
                }
            }
        }
//        binding.calendar.isEnabled = false
    }
}
