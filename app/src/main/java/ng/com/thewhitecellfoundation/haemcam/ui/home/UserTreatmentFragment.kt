package ng.com.thewhitecellfoundation.haemcam.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.DatePicker
import com.applandeo.materialcalendarview.builders.DatePickerBuilder
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import ng.com.thewhitecellfoundation.common.extensions.customOnDrawableRightListener
import ng.com.thewhitecellfoundation.common.extensions.show
import ng.com.thewhitecellfoundation.common.utils.viewBinding
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.ActivityHomeBinding
import ng.com.thewhitecellfoundation.haemcam.databinding.ChemodrugBottomsheetLayoutBinding
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentUserTreamentBinding
import ng.com.thewhitecellfoundation.haemcam.model.DataPair
import ng.com.thewhitecellfoundation.haemcam.model.DrugDays
import ng.com.thewhitecellfoundation.haemcam.model.OtherDrugDays
import ng.com.thewhitecellfoundation.haemcam.ui.adapter.drugDaysView
import ng.com.thewhitecellfoundation.haemcam.ui.adapter.otherDrugDaysView
import ng.com.thewhitecellfoundation.navigation.navigator.extensions.navigator
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [UserTreamentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserTreatmentFragment : Fragment(R.layout.fragment_user_treament) {
    lateinit var behavior: BottomSheetBehavior<ConstraintLayout>
    lateinit var bottomSheetBehaviour: BottomSheetBehaviour

    private val binding by viewBinding(FragmentUserTreamentBinding::bind)
    lateinit var bottomSheetBinding: ChemodrugBottomsheetLayoutBinding
    // Set bottom dialog
    val bottomSheetDialog by lazy {
        BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)
    }
    // Inflate bottom view
    val bottomSheetView by lazy {
        LayoutInflater.from(requireContext()).inflate(
            R.layout.chemodrug_bottomsheet_layout, bottomSheetBinding.root
        )
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottomSheetBehaviour = requireActivity() as BottomSheetBehaviour
        behavior = BottomSheetBehavior.from(bottomSheetBehaviour.parentBinding().mainBs.root)
        behavior.state = BottomSheetBehavior.STATE_HIDDEN

        bottomSheetBinding = ChemodrugBottomsheetLayoutBinding.inflate(layoutInflater)
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
            DrugDays.listOfChemoTherapy.forEach { dd ->
                Log.i("IDS", "${dd.id}")
                drugDaysView {
                    id(dd.id)
                    data(dd)
                    binding.regimenTitleTv.customOnDrawableRightListener {
                        val obj = DrugDays(
                            R.string.regimen, R.array.regimen, null, R.string.chemo_drug,
                            DataPair("", "")
                        )
                        Log.i("Object", "$obj")
                        DrugDays.listOfChemoTherapy.sortBy { it.id }
                        requestModelBuild()
                    }
                    getDrugData { oldIndex, oldItem, newIndex, newItem ->
//                        if(newItem == getString(R.string.chop)){
//
//                        }
//                        behavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED

                        bottomSheetDialog.setContentView(bottomSheetView)
                        bottomSheetDialog.show()
                    }

                    getDaysTimeData { model, parentView, clickedView, position ->

                        var currentTime = System.currentTimeMillis()
                        val tz = TimeZone.getDefault()
                        val cal = GregorianCalendar.getInstance(tz)
                        val offsetInMillis = tz.getOffset(cal.timeInMillis)
                        currentTime -= offsetInMillis.toLong()
                        val date = Date(currentTime)
                        Toast.makeText(context, "$date", Toast.LENGTH_SHORT).show()
                        showDateTimeDialog(date, clickedView)
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

    private fun showDateTimeDialog(date: Date, clickedView: View?) {
        val listener: OnSelectDateListener = OnSelectDateListener {
            for (i in it) {
                Log.i("Calendar", "${i.timeInMillis}")
            }
        }
        val builder = DatePickerBuilder(requireContext(), listener)
            .pickerType(CalendarView.RANGE_PICKER)
        val datePicker: DatePicker = builder.build()
        datePicker.show()
    }

    override fun onResume() {
        super.onResume()
        binding.nextBtn.setOnClickListener {
            navigator.goto(R.id.homeFragment)
        }
    }

    companion object {
        val firstRegimenChemo = DrugDays(
            R.string.regimen, R.array.regimen, null, R.string.chemo_drug,
            DataPair("", "")
        )

        val firstOtherDrugDrug = OtherDrugDays(
            R.string.other_drugs, R.array.diagnosis, R.array.medication_time, R.string.other_drugs,
            DataPair("", "")
        )
    }

    interface BottomSheetBehaviour {
        fun expand()
        fun parentBinding(): ActivityHomeBinding
    }
}
