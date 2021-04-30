package ng.com.thewhitecellfoundation.haemcam.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.skydoves.powerspinner.PowerSpinnerView
import ng.com.thewhitecellfoundation.common.extensions.customOnDrawableRightListener
import ng.com.thewhitecellfoundation.common.utils.viewBinding
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentUserTreamentBinding
import ng.com.thewhitecellfoundation.haemcam.model.DataPair
import ng.com.thewhitecellfoundation.haemcam.model.DrugDays
import ng.com.thewhitecellfoundation.haemcam.model.OtherDrugDays
import ng.com.thewhitecellfoundation.haemcam.ui.adapter.drugDaysView
import ng.com.thewhitecellfoundation.haemcam.ui.adapter.otherDrugDaysView
import ng.com.thewhitecellfoundation.navigation.navigator.extensions.navigator
import ru.slybeaver.slycalendarview.SlyCalendarDialog
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [UserTreamentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserTreatmentFragment : Fragment(R.layout.fragment_user_treament) {
    private val binding by viewBinding(FragmentUserTreamentBinding::bind)

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val regimenTitle = getString(R.string.regimen)
        val firstRegimenChemo = DrugDays(
            R.string.regimen, R.array.regimen, null, R.string.chemo_drug,
            DataPair("", "")
        )

        val firstOtherDrugDrug = OtherDrugDays(
            R.string.other_drugs, R.array.diagnosis, R.array.medication_time, R.string.other_drugs,
            DataPair("", "")
        )

        binding.otherDrugErcv.withModels {
            OtherDrugDays.listOfOtherDrugs.forEach { dd ->

                otherDrugDaysView {
                    id(dd.id)
                    data(dd)
                    binding.otherDrugTitleTv.customOnDrawableRightListener {
                        val obj = OtherDrugDays(
                            R.string.other_drugs, R.array.diagnosis, R.array.medication_time, R.string.other_drugs,
                            DataPair("", "")
                        )

                        OtherDrugDays.listOfOtherDrugs.sortBy { it.id }
                        requestModelBuild()
                    }

                    onDeleteListener { model, parentView, clickedView, position ->
                        model?.data()?.remove
                        parentView.binding.daysTimeSpinner.clearSelectedItem()
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
                    onDeleteListener { model, parentView, clickedView, position ->
                        model.data()?.remove
                        parentView.binding.daysTimeSpinner.hint = getString(R.string.cycle_days)
                        requestModelBuild()
                    }
                }
            }
        }
    }

    private fun showDateTimeDialog(date: Date, clickedView: View?) {
        SlyCalendarDialog()
            .setSingle(false)
            .setHeaderColor(R.color.primaryVariant)
            .setTimeTheme(R.style.SlyCalendarViewTimeTextTheme)
            .setTextColor(R.color.primaryColor)
            .setSelectedTextColor(R.color.primaryVariant)
            .setSelectedColor(R.color.primaryVariant)
            .setStartDate(date)
            .setCallback(object : SlyCalendarDialog.Callback {
                override fun onCancelled() {
                    Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT).show()
                }

                override fun onDataSelected(
                    firstDate: Calendar?,
                    secondDate: Calendar?,
                    hours: Int,
                    minutes: Int
                ) {
                    if (firstDate != null) {
                        if (secondDate == null) {
                            firstDate[Calendar.HOUR_OF_DAY] = hours
                            firstDate[Calendar.MINUTE] = minutes
                            val readableSingleDate = SimpleDateFormat(
                                getString(R.string.timeFormat),
                                Locale.getDefault()
                            ).format(firstDate.time)
                            (clickedView as PowerSpinnerView).hint =
                                readableSingleDate
                        } else {
                            secondDate[Calendar.HOUR_OF_DAY] = hours
                            secondDate[Calendar.MINUTE] = minutes
                            val readableDateRange = getString(
                                R.string.period,
                                SimpleDateFormat(
                                    getString(R.string.dateFormat),
                                    Locale.getDefault()
                                ).format(firstDate.time),
                                SimpleDateFormat(
                                    getString(R.string.timeFormat),
                                    Locale.getDefault()
                                ).format(secondDate.time)
                            )

                            (clickedView as PowerSpinnerView).hint =
                                readableDateRange
                            Toast.makeText(
                                context,
                                "${firstDate.timeInMillis}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            })
            .show(requireActivity().supportFragmentManager, "TAG_SLYCALENDAR")
    }

    override fun onResume() {
        super.onResume()
        binding.nextBtn.setOnClickListener {
            navigator.goto(R.id.homeFragment)
        }
    }
}
