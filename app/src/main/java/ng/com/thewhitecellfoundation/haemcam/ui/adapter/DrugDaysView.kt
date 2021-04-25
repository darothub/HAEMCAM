package ng.com.thewhitecellfoundation.haemcam.ui.adapter

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.skydoves.powerspinner.OnSpinnerItemSelectedListener
import ng.com.thewhitecellfoundation.common.views.dismissPowerViewDropDown
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.DrugDaysItemsLayoutBinding
import ng.com.thewhitecellfoundation.haemcam.model.DrugDays
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
        binding.daysTimeSpinner.tag = tag
        binding.daysTimeSpinner.hint = context.getString(R.string.cycle_days)
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
    fun getDaysTimeData(listener: OnClickListener?) {
        if (listener != null) {
            binding.daysTimeSpinner.setOnClickListener(listener)
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Log.i("DrugDaysView", "Detached")
        dismissPowerViewDropDown(
            binding.drugSpinner,
            binding.daysTimeSpinner
        )
    }
}
