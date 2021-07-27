package ng.com.thewhitecellfoundation.haemcam.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.applandeo.materialcalendarview.EventDay
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.RegimenDrugHorizontalBinding
import ng.com.thewhitecellfoundation.haemcam.model.StringItemData
import java.util.*

@ModelView(
    autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT,
    defaultLayout = R.layout.regimen_drug_horizontal
)
class RegimenDrugAndCalendar @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attr, defStyleAttr) {

    var binding: RegimenDrugHorizontalBinding = RegimenDrugHorizontalBinding.inflate(
        LayoutInflater.from(context),
        this, true
    )

    @ModelProp
    fun setData(drug: StringItemData?) {
        binding.regimenDrugNameTv.text = drug?.str
    }

    @SuppressLint("SetTextI18n")
    @CallbackProp
    fun getDaysTimeData(listener: OnClickListener?) {
        if (listener != null) {
            binding.calendarView.setOnDayClickListener {
//                binding.regimenDrugDateTv.text = context.getString(R.string.days_cycle_formatted, startDay, endDay)
                Log.i("RegimenDrugAndCalendar", it.calendar.timeInMillis.toString())
                val startDate = it.calendar.timeInMillis
                val calendars = ArrayList<Calendar>()
                val midCalendars = ArrayList<Calendar>()
                val events: MutableList<EventDay> = ArrayList()
                var calendar: Calendar? = null
                for (i in 1..3) {
                    calendar = Calendar.getInstance()
                    calendar.timeInMillis = startDate + (86400 * 1000 * i)
                    calendars.add(calendar)
                }

                binding.calendarView.selectedDates = calendars
                binding.calendarView.setHighlightedDays(calendars)
                events.add(EventDay(calendar, null, Color.parseColor("#FF0000")))

                binding.calendarView.setEvents(events)
            }
        }
    }
}
