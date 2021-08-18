package ng.com.thewhitecellfoundation.haemcam.ui.diagnosis

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ng.com.thewhitecellfoundation.common.extensions.onBackDispatcher
import ng.com.thewhitecellfoundation.common.utils.viewBinding
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentBloodGroupSelectionBinding
import ng.com.thewhitecellfoundation.haemcam.model.StringItemData
import ng.com.thewhitecellfoundation.haemcam.ui.adapter.bloodGroupItemView
import ng.com.thewhitecellfoundation.haemcam.ui.home.ButtonAndProgressBarState
import ng.com.thewhitecellfoundation.navigation.navigator.extensions.navigator

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BloodGroupSelectionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BloodGroupSelectionFragment : Fragment(R.layout.fragment_blood_group_selection) {
    private val binding by viewBinding(FragmentBloodGroupSelectionBinding::bind)
    lateinit var buttonAndProgressBarState: ButtonAndProgressBarState

    override fun onAttach(context: Context) {
        super.onAttach(context)
        buttonAndProgressBarState = requireActivity() as ButtonAndProgressBarState
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onBackDispatcher {
            navigator.navController.popBackStack()
        }

        val listOfBloodGroup = arrayListOf(
            StringItemData("A+"),
            StringItemData("A-"),
            StringItemData("AB+"),
            StringItemData("AB-"),
            StringItemData("O+"),
            StringItemData("O-"),
            StringItemData("B+"),
            StringItemData("B-")
        )
//
        binding.epoxyRecyclerView.withModels {
            listOfBloodGroup.forEach { sid ->
                bloodGroupItemView {
                    id(sid.id)
                    data(sid)
                    spanSizeOverride { totalSpanCount, _, _ -> totalSpanCount / 4 }
//                    onClick { _, _, _, _ ->
//                        navigator.goto(R.id.homeFragment)
//                    }
                }
            }
        }
    }
    override fun onStart() {
        super.onStart()
        buttonAndProgressBarState.buttonState("Done") {
            navigator.goto(R.id.homeFragment)
        }
    }
}
