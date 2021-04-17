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
import ng.com.thewhitecellfoundation.common.views.hide
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.DrugDaysItemsLayoutBinding
import ng.com.thewhitecellfoundation.haemcam.model.DrugDays

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
    val a = arrayListOf<String>()

    @ModelProp
    fun setData(data: DrugDays?) {
        if (data?.drug == null) {
            binding.drugSpinner.hide()
            binding.daysTimeSpinner.hide()
        }
        data?.drug?.let { binding.drugSpinner.setItems(it) }
        data?.days?.let { binding.daysTimeSpinner.setItems(it) }
        if (!data?.hint.isNullOrEmpty()) {
            binding.daysTimeSpinner.hint = data?.hint
            binding.drugSpinner.tag = data?.tag
            binding.daysTimeSpinner.tag = data?.tag
        }
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
    fun getDaysTimeData(listener: OnSpinnerItemSelectedListener<String>?) {
        if (listener != null) {
            binding.daysTimeSpinner.setOnSpinnerItemSelectedListener(listener)
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Log.i("DrugDaysView", "Detached")
        binding.daysTimeSpinner.clearSelectedItem()
        binding.drugSpinner.clearSelectedItem()
        dismissPowerViewDropDown(
            binding.drugSpinner,
            binding.daysTimeSpinner
        )
    }
}
