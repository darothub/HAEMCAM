package ng.com.thewhitecellfoundation.haemcam.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.skydoves.powerspinner.OnSpinnerItemSelectedListener
import ng.com.thewhitecellfoundation.common.extensions.dismissPowerViewDropDown
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.DrugDaysItemsLayoutBinding
import ng.com.thewhitecellfoundation.haemcam.model.Regimen
import ng.com.thewhitecellfoundation.haemcam.model.StringItemData
import kotlin.collections.ArrayList

@ModelView(
    autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT,
    defaultLayout = R.layout.drug_days_items_layout
)
class RegimenAndDrugAdapter @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attr, defStyleAttr), DataCallBack {
    var binding: DrugDaysItemsLayoutBinding = DrugDaysItemsLayoutBinding.inflate(
        LayoutInflater.from(context),
        this, true
    )

    @ModelProp
    fun setData(data: Regimen?) {
        Log.i("ID", "${data?.id}")
        data?.drug?.let { binding.drugSpinner.setItems(it) }
        val daysHint = data?.hint?.let { context.getString(it) }
        val tag = data?.tag?.let { context.getString(it) }
        val cycleDays = Array(31) {
            (it + 1).toString()
        }.toList()
        binding.drugSpinner.hint = daysHint + "-" + data?.id
        binding.cycleDaysSpinner.hint = context.getString(R.string.cycle_days)
        binding.cycleDaysSpinner.setItems(cycleDays)
        binding.drugSpinner.tag = tag
    }

    @CallbackProp
    fun setOnDeleteListener(listener: OnClickListener?) {
        binding.removeTv.setOnClickListener(listener)
    }

    @CallbackProp
    fun getDrugData(listener: OnSpinnerItemSelectedListener<String>?) {
        if (listener != null) {
            binding.drugSpinner.setOnSpinnerItemSelectedListener(listener)
        }
    }
    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    @CallbackProp
    fun getDrugData(list: List<StringItemData>?) {
        if (list != null) {
            binding.drugSpinner.setOnSpinnerItemSelectedListener<String> { oldIndex, oldItem, newIndex, newItem ->
                binding.regimenErcv.withModels {
                    list.forEach { cdt ->
                        regimenDrugAndCalendar {
                            id(cdt.id)
                            data(cdt)
                            getDaysTimeData { model, parentView, clickedView, position -> }
                        }
                    }
                }
            }
        }
    }
    @CallbackProp
    fun getDaysTimeData(listener: OnClickListener?) {
        if (listener != null) {
//            binding.daysTimeSpinner.setOnClickListener(listener)
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Log.i("DrugDaysView", "Detached")
        dismissPowerViewDropDown(
            binding.drugSpinner,

        )
    }
    private fun showDateTimeDialog(clickedView: View?, dataCallBack: DataCallBack) {
//        val listOfTimeInMillisecond = arrayListOf<Long>()
//        val listener: OnSelectDateListener = OnSelectDateListener {
//            val startDate = Calendar.getInstance()
//            val endDate = Calendar.getInstance()
//            var month: String? = null
//            var endDateFormat: String? = null
//            var day = ""
//            for (i in it) {
//                listOfTimeInMillisecond.add(i.timeInMillis)
//                val formatter: DateFormat = SimpleDateFormat("MMM", Locale.UK)
//                startDate.timeInMillis = i.timeInMillis
//                day += "${startDate.get(Calendar.DAY_OF_MONTH)}, "
//                endDate.timeInMillis = i.timeInMillis + 1728000000
//                month = formatter.format(startDate.time)
//                endDateFormat = formatter.format(endDate.time)
//
//                Log.i("Calendar", "$day")
//            }
//            val daysInCycle = "$day of $month"
//            (clickedView as TextView).text = daysInCycle
//            dataCallBack.getTimeInMilliSecondsList(listOfTimeInMillisecond)
//        }
//
//        val builder = DatePickerBuilder(context, listener)
//            .setPickerType(CalendarView.MANY_DAYS_PICKER)
//        val datePicker: DatePicker = builder
//            .setHeaderColor(R.color.primaryColor)
//            .setSelectionColor(R.color.primaryColor)
//            .build()
//        datePicker.show()
    }

    override fun getTimeInMilliSecondsList(list: ArrayList<Long>): List<Long> {
        Log.i("ListOfTime", "$list")
        return list
    }
}
interface DataCallBack {
    fun getTimeInMilliSecondsList(list: ArrayList<Long>): List<Long>
}
