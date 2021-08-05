package ng.com.thewhitecellfoundation.haemcam.ui.diagnosis

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import ng.com.thewhitecellfoundation.common.extensions.customOnDrawableRightListener
import ng.com.thewhitecellfoundation.common.utils.viewBinding
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.ChemodrugBottomsheetLayoutBinding
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentRegimenAndDrugsBinding
import ng.com.thewhitecellfoundation.haemcam.model.DataPair
import ng.com.thewhitecellfoundation.haemcam.model.OtherDrugDays
import ng.com.thewhitecellfoundation.haemcam.model.Regimen
import ng.com.thewhitecellfoundation.haemcam.model.StringItemData
import ng.com.thewhitecellfoundation.haemcam.ui.adapter.otherDrugDaysView
import ng.com.thewhitecellfoundation.haemcam.ui.adapter.regimenAndDrugAdapter
import ng.com.thewhitecellfoundation.haemcam.ui.home.ButtonAndProgressBarState
import ng.com.thewhitecellfoundation.haemcam.ui.main.BaseFragment
import ng.com.thewhitecellfoundation.navigation.navigator.extensions.navigator
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [UserTreamentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegimenAndDrugsFragment : BaseFragment(R.layout.fragment_regimen_and_drugs) {
    private val binding by viewBinding(FragmentRegimenAndDrugsBinding::bind)
    lateinit var bottomSheetBinding: ChemodrugBottomsheetLayoutBinding
    override val buttonAndProgressBarState: ButtonAndProgressBarState by lazy {
        requireActivity() as ButtonAndProgressBarState
    }

    // Set bottom dialog
    private val bottomSheetDialog by lazy {
        BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottomSheetBinding = ChemodrugBottomsheetLayoutBinding.inflate(layoutInflater)

        val listOfRegimenDrugs = arrayListOf(
            StringItemData("Chemosyspathom"),
            StringItemData("Chemosyspaloam"),
            StringItemData("Chemosysthahah")
        )
        binding.otherDrugErcv.withModels {
            OtherDrugDays.listOfOtherDrugs.forEach { dd ->

                otherDrugDaysView {
                    id(dd.id)
                    data(dd)
                    binding.otherDrugTitleTv.customOnDrawableRightListener {
                        val obj = OtherDrugDays(
                            R.string.other_drugs,
                            R.array.diagnosis,
                            R.array.medication_time,
                            R.string.other_drugs,
                            DataPair("", "")
                        )

                        OtherDrugDays.listOfOtherDrugs.sortBy { it.id }
                        requestModelBuild()
                    }

                    onDeleteListener { model, parentView, clickedView, position ->
                        model?.data()?.remove
//                        parentView.binding.daysTimeSpinner.clearSelectedItem()
                        requestModelBuild()
                    }
                }
            }
        }

        binding.regimenErcv.withModels {
            Regimen.listOfChemoTherapy.forEach { dd ->
                regimenAndDrugAdapter {
                    id(dd.id)
                    data(dd)
                    getDrugData(listOfRegimenDrugs)
                    getDaysTimeData { model, parentView, clickedView, position ->
                    }
                    onDeleteListener { model, parentView, _, _ ->
                        model.data()?.remove
//                        parentView.binding.daysTimeSpinner.hint = getString(R.string.cycle_days)
                        requestModelBuild()
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        buttonAndProgressBarState.buttonState("Skip/Next") {
            navigator.goto(R.id.homeFragment)
        }
    }

    companion object {
        val firstRegimenChemo = Regimen(
            R.string.regimen, R.array.regimen, null, R.string.chemo_drug,
            DataPair("", "")
        )

        val firstOtherDrugDrug = OtherDrugDays(
            R.string.other_drugs, R.array.diagnosis, R.array.medication_time, R.string.other_drugs,
            DataPair("", "")
        )
    }
}
