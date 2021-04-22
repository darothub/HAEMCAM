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
import ng.com.thewhitecellfoundation.haemcam.model.DrugDaysBase
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

    @ModelProp(options = [ModelProp.Option.DoNotHash])
    fun setData(data: DrugDaysBase?) {
        Log.i("ID", "${data?.id}")
        if (data?.drug == null) {
            binding.drugSpinner.hide()
            binding.daysTimeSpinner.hide()
        }
        data?.drug?.let { binding.drugSpinner.setItems(it) }
        if (!data?.hint.isNullOrEmpty()) {
            if (data?.hint == context.getString(R.string.chemo_drug)) {
                val id = data?.id?.plus(1.toLong())
                binding.drugSpinner.hint = data?.hint + "-" + id
            }
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
    fun getDaysTimeData(listener: OnClickListener?) {
        if (listener != null) {
            binding.daysTimeSpinner.setOnClickListener(listener)
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
