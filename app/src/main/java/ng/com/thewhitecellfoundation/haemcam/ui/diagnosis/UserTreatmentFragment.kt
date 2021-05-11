package ng.com.thewhitecellfoundation.haemcam.ui.diagnosis

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.DatePicker
import com.applandeo.materialcalendarview.builders.DatePickerBuilder
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.skydoves.powerspinner.PowerSpinnerView
import ng.com.thewhitecellfoundation.common.extensions.customOnDrawableRightListener
import ng.com.thewhitecellfoundation.common.utils.viewBinding
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.ChemodrugBottomsheetLayoutBinding
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentUserTreamentBinding
import ng.com.thewhitecellfoundation.haemcam.model.DataPair
import ng.com.thewhitecellfoundation.haemcam.model.DrugDays
import ng.com.thewhitecellfoundation.haemcam.model.OtherDrugDays
import ng.com.thewhitecellfoundation.haemcam.model.StringItemData
import ng.com.thewhitecellfoundation.haemcam.ui.adapter.drugDaysView
import ng.com.thewhitecellfoundation.haemcam.ui.adapter.otherDrugDaysView
import ng.com.thewhitecellfoundation.navigation.navigator.extensions.navigator
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [UserTreamentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserTreatmentFragment : Fragment(R.layout.fragment_user_treament) {
    private val binding by viewBinding(FragmentUserTreamentBinding::bind)
    lateinit var bottomSheetBinding: ChemodrugBottomsheetLayoutBinding
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
            StringItemData(

                "Chemosyspaloam"
            ),
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
                    getDrugData(listOfRegimenDrugs)
                    getDaysTimeData { model, parentView, clickedView, position ->

                        var currentTime = System.currentTimeMillis()
                        val tz = TimeZone.getDefault()
                        val cal = GregorianCalendar.getInstance(tz)
                        val offsetInMillis = tz.getOffset(cal.timeInMillis)
                        currentTime -= offsetInMillis.toLong()
                        val date = Date(currentTime)
                        Toast.makeText(context, "$date", Toast.LENGTH_SHORT).show()
                        showDateTimeDialog(clickedView)
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

    private fun showDateTimeDialog(clickedView: View?) {
        val listener: OnSelectDateListener = OnSelectDateListener {
            val startDate = Calendar.getInstance()
            val endDate = Calendar.getInstance()
            var startDateFormat: String? = null
            var endDateFormat: String? = null
            for (i in it) {

                val formatter: DateFormat = SimpleDateFormat.getDateInstance()
                startDate.timeInMillis = i.timeInMillis
                endDate.timeInMillis = i.timeInMillis + 1728000000
                startDateFormat = formatter.format(startDate.time)
                endDateFormat = formatter.format(endDate.time)
                Log.i("Calendar", "$endDateFormat")
            }
            (clickedView as PowerSpinnerView).hint = "$startDateFormat to $endDateFormat"
        }
        val builder = DatePickerBuilder(requireContext(), listener)
            .setPickerType(CalendarView.ONE_DAY_PICKER)
        val datePicker: DatePicker = builder
            .setHeaderColor(R.color.primaryColor)
            .setSelectionColor(R.color.primaryColor)
            .build()
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
}
