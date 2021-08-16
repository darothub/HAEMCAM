package ng.com.thewhitecellfoundation.haemcam.ui.medication

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ng.com.thewhitecellfoundation.common.utils.viewBinding
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentOtherDrugInfoBinding
import ng.com.thewhitecellfoundation.haemcam.model.StringItemData
import ng.com.thewhitecellfoundation.haemcam.ui.adapter.chemoTherapyEndView
import ng.com.thewhitecellfoundation.haemcam.ui.adapter.chemoTherapyHeaderView
import ng.com.thewhitecellfoundation.haemcam.ui.adapter.chemoTherapyListView
import ng.com.thewhitecellfoundation.haemcam.ui.home.HomeBaseFragment
import ng.com.thewhitecellfoundation.navigation.navigator.extensions.navigator

/**
 * A simple [Fragment] subclass.
 * Use the [OtherDrugInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OtherDrugInfoFragment : HomeBaseFragment(R.layout.fragment_other_drug_info) {
    private val binding by viewBinding(FragmentOtherDrugInfoBinding::bind)
    override val toolLeftImageDrawable: Int?
        get() = R.drawable.ic_baseline_keyboard_backspace_24

    override val toolBarTitle: String?
        get() = getString(R.string.medication)
    override val leftAction: () -> Unit
        get() = {
            navigator.navController.popBackStack()
        }
    override val toolRightImageDrawable: Int
        get() = R.drawable.ic_settings_icon

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val chemoTestString = arrayListOf(StringItemData("Drug 1"), StringItemData("Drug 2"), StringItemData("Drug 3"), StringItemData("Drug 3"), StringItemData("Drug 3"))

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
                            onClick { model, parentView, clickedView, position ->
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
