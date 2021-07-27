package ng.com.thewhitecellfoundation.haemcam.ui.adapter

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.ServicesButtonItemBinding
import ng.com.thewhitecellfoundation.haemcam.model.StringItemData

@ModelView(
    autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT,
    defaultLayout = R.layout.services_button_item
)
class ServiceButtonItemView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attr, defStyleAttr) {

    var binding: ServicesButtonItemBinding = ServicesButtonItemBinding.inflate(
        LayoutInflater.from(context),
        this, true
    )
    @ModelProp
    fun setData(data: StringItemData?) {
        binding.servicesBtn.text = data?.str
    }
    @CallbackProp
    fun onClick(listener: OnClickListener?) {
        if (listener != null) {
            binding.servicesBtn.setOnClickListener(listener)
        }
    }
}
