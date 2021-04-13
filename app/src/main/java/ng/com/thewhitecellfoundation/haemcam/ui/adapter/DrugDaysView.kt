package ng.com.thewhitecellfoundation.haemcam.ui.adapter

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import ng.com.thewhitecellfoundation.common.views.hide
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.DrugDaysItemsLayoutBinding
import ng.com.thewhitecellfoundation.haemcam.model.DrugDays

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT, defaultLayout = R.layout.drug_days_items_layout)
class DrugDaysView @JvmOverloads constructor(context: Context, attr: AttributeSet? = null, defStyleAttr: Int = 0) :
    ConstraintLayout(context, attr, defStyleAttr) {
    var binding: DrugDaysItemsLayoutBinding = DrugDaysItemsLayoutBinding.inflate(
        LayoutInflater.from(context),
        this, true
    )
    val a = arrayListOf<String>()
    @ModelProp
    fun setData(data: DrugDays?) {

        data?.drug?.let { a.addAll(it) }
        if (data?.drug?.isEmpty() == true) {
            binding.drugSpinner.hide()
            binding.daysTimeSpinner.hide()
        }
        binding.drugSpinner.setItems(R.array.diagnosis)
        if (!data?.hint.isNullOrEmpty()) {
            binding.daysTimeSpinner.hint = data?.hint
        }
    }

    @CallbackProp
    fun setOnDeleteListener(listener: OnClickListener?) {
        binding.removeTv.setOnClickListener(listener)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        binding.drugSpinner.dismiss()
        binding.daysTimeSpinner.dismiss()
    }
}
