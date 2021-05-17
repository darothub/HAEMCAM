package ng.com.thewhitecellfoundation.haemcam.ui.adapter

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.DrugListLayoutBinding
import ng.com.thewhitecellfoundation.haemcam.model.StringItemData

@ModelView(
    autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT,
    defaultLayout = R.layout.drug_list_layout
)
class DrugNameView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    ConstraintLayout(context, attr, defStyleAttr) {

    var binding: DrugListLayoutBinding = DrugListLayoutBinding.inflate(
        LayoutInflater.from(context),
        this, true
    )

    @ModelProp
    fun setData(drug: StringItemData?) {
        binding.drugEt.setText(drug?.str)
        binding.daysTimeSpinner.hint = context.getString(R.string.start_date)
    }

    @CallbackProp
    fun getDaysTimeData(listener: OnClickListener?) {
        if (listener != null) {
            binding.daysTimeSpinner.setOnClickListener(listener)
        }
    }
}
