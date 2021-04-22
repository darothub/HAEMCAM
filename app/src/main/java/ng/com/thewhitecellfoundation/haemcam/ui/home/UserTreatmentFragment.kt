package ng.com.thewhitecellfoundation.haemcam.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.skydoves.powerspinner.PowerSpinnerView
import ng.com.thewhitecellfoundation.common.utils.viewBinding
import ng.com.thewhitecellfoundation.common.views.customOnDrawableRightListener
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentUserTreamentBinding
import ng.com.thewhitecellfoundation.haemcam.model.DataPair
import ng.com.thewhitecellfoundation.haemcam.model.DrugDays
import ng.com.thewhitecellfoundation.haemcam.ui.adapter.drugDaysView
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

        val chemoTitle = getString(R.string.chemo_drug)
        val otherDrugTitle = getString(R.string.other_drugs)
        val regimenTitle = getString(R.string.regimen)
        val list = arrayListOf<DrugDays>(
            DrugDays(
                regimenTitle, R.array.diagnosis, null, getString(R.string.chemo_drug),
                DataPair("", "")
            ),
        )

        binding.regimenErcv.withModels {
            list.forEach { dd ->
                drugDaysView {
                    id(dd.id)
                    data(dd)
                    binding.titleTv.customOnDrawableRightListener {
                        list.add(
                            DrugDays(
                                regimenTitle,
                                R.array.diagnosis,
                                null,
                                getString(R.string.chemo_drug),
                                DataPair("", "")
                            )
                        )
                        requestModelBuild()
                    }

                    getDaysTimeData { model, parentView, clickedView, position ->

                        var currentTime = System.currentTimeMillis()
                        val tz = TimeZone.getDefault()
                        val cal = GregorianCalendar.getInstance(tz)
                        val offsetInMillis = tz.getOffset(cal.timeInMillis)
                        currentTime -= offsetInMillis.toLong()
                        val date = Date(currentTime)
                        Log.i("Date", "Date:$date\nTimeZone:$tz\nCalendar:$cal\nOffset$offsetInMillis\nCurrentTime:$currentTime ")
                        Toast.makeText(context, "$date", Toast.LENGTH_SHORT).show()
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
                    onDeleteListener { model, parentView, clickedView, position ->
                        list.removeAt(position)
                        DrugDays.idPlaceholder -= 1
                        Log.i("DataList", "${list.size}")
                        requestModelBuild()
                    }
                }
            }
        }
//        val listOfTitleAndListType = arrayListOf(
//            TitleAndListType<DrugDays>(regimenTitle, list, getString(R.string.cycle_days)),
//            TitleAndListType(otherDrugTitle, list, getString(R.string.time_of_medication))
//        )

//        binding.ercv.withModels {
//            listOfTitleAndListType.forEach { tALT ->
//                titleAndListTypeView {
//                    Log.i("TitleObjec", "$tALT id ${tALT.id}")
//                    id(tALT.id)
//                    data(tALT)
//                    when (tALT.title) {
//                        otherDrugTitle -> {
//                            onAddListener(DrugDays(getString(R.string.other_drugs), R.array.diagnosis, R.array.medication_time, getString(R.string.time_of_medication), DataPair("", "")))
//                        }
//                        regimenTitle -> {
//                            onAddListener(DrugDays(getString(R.string.regimen), R.array.regimen, R.array.medication_time, getString(R.string.cycle_days), DataPair("", "")))
//                        }
//                    }
//                }
//            }
//            binding.nextBtn.setOnClickListener {
//                val t = TitleAndListTypeView.tdp
//                t.values.forEach {
//                    it.remove(DataPair("", ""))
//                }
//                val incomplete = t.entries.stream().anyMatch {
//                    it.value.any { d -> d.first == "" || d.second == "" }
//                }
//                if (incomplete) {
//                    Log.i("tdp", "$t")
//                    Toast.makeText(context, "Some input(s) are nt complete", Toast.LENGTH_SHORT).show()
//                    return@setOnClickListener
//                }
//                Toast.makeText(context, "Complete $t", Toast.LENGTH_SHORT).show()
//                Log.i("tdp", "$t")
//                (requireActivity() as Navigator).goto(R.id.homeFragment)
//            }
//        }
    }
}
