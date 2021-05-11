package ng.com.thewhitecellfoundation.haemcam.ui.adapter

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.ChemodrugItemLayoutBinding
import ng.com.thewhitecellfoundation.haemcam.model.StringItemData

@ModelView(
    autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT,
    defaultLayout = R.layout.chemodrug_item_layout
)
class ChemoTherapyListView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attr, defStyleAttr) {
    var binding: ChemodrugItemLayoutBinding = ChemodrugItemLayoutBinding.inflate(
        LayoutInflater.from(context),
        this, true
    )

    @ModelProp
    fun setData(data: StringItemData?) {
        binding.chemoDrugTv3.text = data?.str
    }
}
