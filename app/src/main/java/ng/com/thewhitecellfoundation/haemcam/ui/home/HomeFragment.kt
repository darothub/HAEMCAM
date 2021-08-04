package ng.com.thewhitecellfoundation.haemcam.ui.home

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ng.com.thewhitecellfoundation.common.utils.viewBinding
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentHomeBinding
import ng.com.thewhitecellfoundation.haemcam.model.HomeItemData
import ng.com.thewhitecellfoundation.haemcam.ui.adapter.homeItemView
import ng.com.thewhitecellfoundation.haemcam.ui.transfusion.TransfusionFragment
import ng.com.thewhitecellfoundation.navigation.navigator.extensions.navigator

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding by viewBinding(FragmentHomeBinding::bind)
    lateinit var buttonAndProgressBarState: ButtonAndProgressBarState

    override fun onAttach(context: Context) {
        super.onAttach(context)
        buttonAndProgressBarState = requireActivity() as ButtonAndProgressBarState
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listOfHomeItem = arrayListOf(
            HomeItemData(R.drawable.ic_medication_icon, R.string.medication),
            HomeItemData(R.drawable.ic_nutrition_icon, R.string.nutrition),
            HomeItemData(R.drawable.ic_lab_result_icon, R.string.lab_result),
            HomeItemData(R.drawable.ic_transfusion_icon, R.string.transfusion),
        )
        binding.clickMe.setOnClickListener {
            val fragment = TransfusionFragment()
            val fragmentManager = activity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.homee, fragment)
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()
        }

        binding.ercv.withModels {
            listOfHomeItem.forEach { hid ->
                homeItemView {
                    id(hid.id)
                    data(hid)
                    spanSizeOverride { totalSpanCount, _, _ -> totalSpanCount / 2 }

                    onClick { model, parentView, clickedView, position ->
                        when (clickedView.tag) {
                            getString(R.string.medication) -> navigator.goto(R.id.servicesFragment)
                            getString(R.string.lab_result) -> navigator.goto(R.id.labResultsFragment)
                            getString(R.string.nutrition) -> navigator.goto(R.id.nutritionMenuFragment)
                        }
                    }
                }
            }
        }
    }
}
