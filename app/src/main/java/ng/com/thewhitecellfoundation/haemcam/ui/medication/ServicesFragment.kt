package ng.com.thewhitecellfoundation.haemcam.ui.medication

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ng.com.thewhitecellfoundation.common.utils.viewBinding
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentServicesBinding
import ng.com.thewhitecellfoundation.haemcam.model.StringItemData
import ng.com.thewhitecellfoundation.haemcam.ui.adapter.serviceButtonItemView
import ng.com.thewhitecellfoundation.haemcam.ui.home.HomeBaseFragment
import ng.com.thewhitecellfoundation.navigation.navigator.extensions.navigator

/**
 * A simple [Fragment] subclass.
 * Use the [ServicesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ServicesFragment : HomeBaseFragment(R.layout.fragment_services) {
    private val binding by viewBinding(FragmentServicesBinding::bind)
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

        val services = arrayListOf(
            StringItemData(getString(R.string.chemotherapy)),
            StringItemData(getString(R.string.other_drug)),
            StringItemData(getString(R.string.side_effect_reporting)),
            StringItemData(getString(R.string.blood_test)),
        )
//
        binding.epoxyRecyclerView.withModels {
            services.forEach { sid ->
                serviceButtonItemView {
                    id(sid.id)
                    data(sid)
                    onClick { model, parentView, clickedView, position ->
                        when (model.data()?.str) {
                            getString(R.string.chemotherapy) -> {
                                navigator.goto(R.id.chemoTherapyFragment)
                            }
                            getString(R.string.other_drug) -> {
                                navigator.goto(R.id.otherDrugFragment)
                            }
                            getString(R.string.side_effect_reporting) -> {
                                navigator.goto(R.id.sideEffectReportingFragment)
                            }
                            getString(R.string.blood_test) -> {
                                navigator.goto(R.id.labResultsFragment)
                            }
                        }
                    }
                }
            }
        }
    }
}
