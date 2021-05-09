package ng.com.thewhitecellfoundation.haemcam.ui.adapter

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.DatePicker
import com.applandeo.materialcalendarview.builders.DatePickerBuilder
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener
import com.skydoves.powerspinner.OnSpinnerItemSelectedListener
import ng.com.thewhitecellfoundation.common.extensions.dismissPowerViewDropDown
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.DrugDaysItemsLayoutBinding
import ng.com.thewhitecellfoundation.haemcam.model.DrugDays
import ng.com.thewhitecellfoundation.haemcam.ui.medication.ChemoDrugTest
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@ModelView(
    autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT,
    defaultLayout = R.layout.drug_days_items_layout
)
class DrugDaysView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    ConstraintLayout(context, attr, defStyleAttr) {
    var binding: DrugDaysItemsLayoutBinding = DrugDaysItemsLayoutBinding.inflate(
        LayoutInflater.from(context),
        this, true
    )

    @ModelProp
    fun setData(data: DrugDays?) {
        Log.i("ID", "${data?.id}")
        data?.drug?.let { binding.drugSpinner.setItems(it) }
        val daysHint = data?.hint?.let { context.getString(it) }
        val tag = data?.tag?.let { context.getString(it) }
        binding.drugSpinner.hint = daysHint + "-" + data?.id
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
    @CallbackProp
    fun getDrugData(list: List<ChemoDrugTest>?) {
        if (list != null) {
            binding.drugSpinner.setOnSpinnerItemSelectedListener<String> { oldIndex, oldItem, newIndex, newItem ->
                binding.regimenErcv.withModels {
                    list.forEach { cdt ->
                        drugNameView2 {
                            id(cdt.id)
                            data(cdt)
                            getDaysTimeData { model, parentView, clickedView, position ->
                                showDateTimeDialog(clickedView)
                            }
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
            (clickedView as TextView).text = "$startDateFormat to $endDateFormat"
        }
        val builder = DatePickerBuilder(context, listener)
            .setPickerType(CalendarView.ONE_DAY_PICKER)
        val datePicker: DatePicker = builder
            .setHeaderColor(R.color.primaryColor)
            .setSelectionColor(R.color.primaryColor)
            .build()
        datePicker.show()
    }
}
