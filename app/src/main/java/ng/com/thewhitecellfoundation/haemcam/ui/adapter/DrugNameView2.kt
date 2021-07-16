package ng.com.thewhitecellfoundation.haemcam.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.RegimenDrugHorizontalBinding
import ng.com.thewhitecellfoundation.haemcam.model.StringItemData
import java.util.*

@ModelView(
    autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT,
    defaultLayout = R.layout.regimen_drug_horizontal
)
class DrugNameView2 @JvmOverloads constructor(
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
            binding.calendarView.buildCalendar()
            binding.regimenDrugDateTv.setOnClickListener(listener)
            binding.calendarView.setDateFormat("yyyy MMM")
            binding.calendarView.setPreventPreviousDate(true)
            binding.calendarView.setErrToastMessage("You can not select the previous date.")
            binding.calendarView.setOnDaySelectedListener { startDay, endDay ->
                binding.regimenDrugDateTv.text = context.getString(R.string.days_cycle_formatted, startDay, endDay)
            }
        }
    }
}
