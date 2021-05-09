package ng.com.thewhitecellfoundation.haemcam.ui.adapter

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.RegimenDrugHorizontalBinding
import ng.com.thewhitecellfoundation.haemcam.ui.medication.ChemoDrugTest

@ModelView(
    autoLayout = ModelView.Size.WRAP_WIDTH_WRAP_HEIGHT,
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
    fun setData(drug: ChemoDrugTest?) {
        binding.regimenDrugNameTv.text = drug?.str
    }

    @CallbackProp
    fun getDaysTimeData(listener: OnClickListener?) {
        if (listener != null) {
            binding.regimenDrugDateTv.setOnClickListener(listener)
        }
    }
}
